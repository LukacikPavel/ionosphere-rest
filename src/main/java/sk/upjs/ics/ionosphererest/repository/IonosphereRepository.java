package sk.upjs.ics.ionosphererest.repository;

import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import sk.upjs.ics.ionosphererest.model.Ionosphere;
import sk.upjs.ics.ionosphererest.model.Station;

import java.time.Instant;
import java.util.List;

public interface IonosphereRepository extends CassandraRepository<Ionosphere, MapId> {

    @AllowFiltering
    List<Ionosphere> findByStationAndTimeStartGreaterThanEqualAndTimeEndLessThanEqual(String station, Instant time_start, Instant time_end);

    @AllowFiltering
    List<Ionosphere> findByTimeStartGreaterThanEqualAndTimeEndLessThanEqualAndAzimuthStartGreaterThanEqualAndAzimuthEndLessThanEqualAndElevationStartGreaterThanEqualAndElevationEndLessThanEqual
            (Instant timeStart, Instant timeEnd, int azimuthStart, int azimuthEnd, int elevationStart, int elevationEnd);

    @Query(value = "SELECT DISTINCT station FROM ionosphere;")
    List<Station> getDistinctStations();

}
