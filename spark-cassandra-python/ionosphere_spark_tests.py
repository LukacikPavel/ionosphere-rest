import pytest
import unittest

from pyspark import SparkContext
from pyspark.sql.functions import  avg
from pyspark.sql import SparkSession
from pyspark.sql.functions import round

from contextlib import contextmanager
from pyspark_test import assert_pyspark_df_equal

from ionosphere_spark_main import getTime, getAzimuthInterval, getElevationInterval, group_by_and_avg



pytestmark = pytest.mark.usefixtures("spark_session")

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



class TestStringMethods(unittest.TestCase):

    def test_get_time(self):

        date_time_str = '2015-12-31 23:01:00'
        date_time_expected = '2015-12-31T23to2016-01-01T0'
        date_time_using_function = getTime(date_time_str)

        self.assertEqual(date_time_using_function, date_time_expected)

    def test_get_azimuth(self):

        azimuth_str = '210.0'
        azimuth_expected = '210-240'
        azimuth_using_function = getAzimuthInterval(azimuth_str)

        self.assertEqual(azimuth_using_function, azimuth_expected)

    def test_get_elevation(self):

        elevation_str = '49.0'
        elevation_expected = '40-50'
        elevation_using_function = getElevationInterval(elevation_str)

        self.assertEqual(elevation_using_function, elevation_expected)


def test_group_by_and_avg(spark_session):

    dataframe = spark_session.read.csv("test_dataset.csv", header = True, inferSchema = True)
    df = group_by_and_avg(dataframe)
    df = df.orderBy('station', 'time_start','time_end','azimuth_start','azimuth_end','elevation_start', 'elevation_end')
    
    dataFrameResults = spark_session.read.csv("test_dataset_results.csv", header = True, inferSchema = True)
    dataFrameResults = dataFrameResults.orderBy('station', 'time_start','time_end','azimuth_start','azimuth_end','elevation_start', 'elevation_end')
    
    assert_pyspark_df_equal(df, dataFrameResults)


if __name__ == '__main__':

    spark = SparkSession.builder\
        .master("local[*]")\
        .appName("spark")\
        .config('spark.ui.port', '4050')\
        .getOrCreate()

    with spark_timezone("UTC"):
        test_group_by_and_avg(spark)

    unittest.main()