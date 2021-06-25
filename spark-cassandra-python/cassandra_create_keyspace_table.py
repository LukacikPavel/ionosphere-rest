from cassandra.cluster import Cluster

CLUSTER_SERVERS = ['127.0.0.1']
KEYSPACE = 'mykeyspace'
TABLE = 'ionosphere'


def main():
    cluster = Cluster(CLUSTER_SERVERS)
    session = cluster.connect()

    session.execute("""
            CREATE KEYSPACE IF NOT EXISTS %s
            WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': '1' }
            """ % KEYSPACE)

    session.set_keyspace(KEYSPACE)

    session.execute("""
            CREATE TABLE %s (
                station text,
                timeStart timestamp,
                timeEnd timestamp,
                azimuthStart int,
                azimuthEnd int,
                elevationStart int,
                elevationEnd int,
                tecu double,
                s4 double,
                sigmaphi double,
                PRIMARY KEY (station, azimuthStart, elevationStart, timeStart)
            );
            """ % TABLE)

    session.shutdown()


if __name__ == "__main__":
    main()
