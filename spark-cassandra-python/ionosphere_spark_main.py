import sys
from pyspark.sql.functions import udf, avg
from pyspark.sql import SparkSession
from contextlib import contextmanager
from pyspark.sql.types import StringType
from datetime import datetime
import math
import datetime as dt

from pyspark.sql.functions import split
import pyspark.sql.functions as F
from pyspark.sql.functions import round


def getTime(datetime_str):
  datetime_obj = datetime.strptime(datetime_str, "%Y-%m-%d %H:%M:%S")
  date_and_time = datetime_obj.strftime("%Y-%m-%d %H:%M:%S").split(" ")

  time = date_and_time[1]
  start = int(time.split(":")[0])
    

  if start == 23:
    end = 0
    date_str = date_and_time[0]
    date_obj = datetime.strptime(date_str, '%Y-%m-%d')
    date_obj = date_obj + dt.timedelta(days=1)
    date = date_obj.strftime("%Y-%m-%d")

  else:
    end = start + 1
    date = date_and_time[0]

  return str(date_and_time[0])+"T"+str(start)+"to"+str(date)+"T"+str(end)

def getAzimuthInterval(azimuth):
  startOfInterval = math.floor(float(azimuth)/30) * 30
  if startOfInterval == 360:
    startOfInterval = 330
  return str(startOfInterval)+"-"+str(startOfInterval + 30)

def getElevationInterval(elevation):
  startOfInterval = math.floor(float(elevation)/10) * 10
  if startOfInterval == 90:
    startOfInterval = 80
  return str(startOfInterval)+"-"+str(startOfInterval + 10)

def read_data(spark, path):
  dataFrame = spark.read.csv(path, header=True, inferSchema=True)
  return dataFrame

def edit_dataframe(dataFrame2):

  dataFrame2 = dataFrame.select("datetime","azimuth [deg]", "elevation [deg]", "CHAIN station", "TEC at current time [TECU]", "S4 [dimensionless]", "sigmaPhi [radians]")
  
  getTimeUdf = udf(getTime,StringType())
  getAzimuthUdf = udf(getAzimuthInterval, StringType())
  getElevationUdf = udf(getElevationInterval, StringType())

  dataFrame2 = dataFrame2.withColumn("Time",getTimeUdf("datetime"))
  dataFrame2 = dataFrame2.withColumn("Azimuth", getAzimuthUdf("azimuth [deg]"))
  df = dataFrame2.withColumn("Elevation", getElevationUdf("elevation [deg]"))

  split_col = split(df['Time'], 'to')
  df = df.withColumn('time_start', F.date_format(split_col.getItem(0),"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
  df = df.withColumn('time_end', F.date_format(split_col.getItem(1),"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
  df = df.drop("Time")

  split_col = split(df['Azimuth'], '-')
  df = df.withColumn('azimuth_start', split_col.getItem(0))
  df = df.withColumn('azimuth_end', split_col.getItem(1))
  df = df.drop("Azimuth")

  split_col = split(df['Elevation'], '-')
  df = df.withColumn('elevation_start', split_col.getItem(0))
  df = df.withColumn('elevation_end', split_col.getItem(1))
  df = df.drop("Elevation")

  df = df.withColumnRenamed("CHAIN station", "station")

  return df

def group_by_and_avg(df):
  df = df.groupby("station", "time_start", "time_end", "azimuth_start", "azimuth_end", "elevation_start", "elevation_end").agg(round(avg("TEC at current time [TECU]"),2).alias("tecu"), round(avg("S4 [dimensionless]"),2).alias("s4"), round(avg("sigmaPhi [radians]"),2).alias("sigmaphi"))
  return df

def write_data_to_cassandra(df):
  df = df.toDF(*(c.replace('_', '') for c in df.columns))
  df.write.format("org.apache.spark.sql.cassandra").mode('append').options(table="ionosphere", keyspace="mykeyspace").save()

# SET UTC TIMEZONE WHILE READING CSV FILE
@contextmanager
def spark_timezone(timezone: str):
    spark = get_active_spark_context()
    current = spark.conf.get("spark.sql.session.timeZone")
    spark.conf.set("spark.sql.session.timeZone", timezone)

    try:
        yield None
    finally:
        spark.conf.set("spark.sql.session.timeZone", current)

def get_active_spark_context() -> SparkSession:
    return SparkSession.builder.getOrCreate()


if __name__ == "__main__":

  args = sys.argv[1:]
  spark = SparkSession.builder\
    .master("local[*]")\
    .appName("spark")\
    .config('spark.ui.port', '4050')\
    .getOrCreate()

  with spark_timezone("UTC"):
    dataFrame = read_data(spark, args)
    dataFrame = edit_dataframe(dataFrame)
    dataFrame = group_by_and_avg(dataFrame)
    write_data_to_cassandra(dataFrame)

  spark.stop()

