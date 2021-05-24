package sk.upjs.ics.ionosphererest.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import sk.upjs.ics.ionosphererest.model.Ionosphere;
import sk.upjs.ics.ionosphererest.model.Tecu;

import java.time.LocalDateTime;
import java.util.List;

public interface IonosphereRepository extends CassandraRepository<Ionosphere, Long> {
    @AllowFiltering
    List<Ionosphere> findByStation(String station);

    @AllowFiltering
    List<Ionosphere> findByTimeStart(LocalDateTime time_start);

    @AllowFiltering
    List<Ionosphere> findByStationAndTimeStart(String station, LocalDateTime time_start);

    @AllowFiltering
    <T> List<T> findByStationAndTimeStartGreaterThanEqualAndTimeEndLessThanEqual(Class<T> type, String station, LocalDateTime time_start, LocalDateTime time_end);

    @AllowFiltering
    <T> List<T> findByTimeStartGreaterThanEqualAndTimeEndLessThanEqual(Class<T> type, LocalDateTime time_start, LocalDateTime time_end);
}
