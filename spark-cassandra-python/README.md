### Python 3.7.9 - install packages 
`pip install -r requirements.txt`

### Create keyspace and table in Cassandra
`python cassandra_create_keyspace_table.py`

### Process datasets and write into Cassandra using Spark (spark-3.1.1-hadoop3.2)

`spark-submit --master local[4] --packages com.datastax.spark:spark-cassandra-connector_2.12:3.0.1 --conf spark.cassandra.connection.host=127.0.0.1 .\ionosphere_spark_main.py .\ml_db_2015_NaNsDropped.csv .\ml_db_2016_NaNsDropped.csv`

### Run tests
`spark-submit ionosphere_spark_tests.py`
