package sk.upjs.ics.ionosphererest.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import sk.upjs.ics.ionosphererest.model.Ionosphere;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface IonosphereRepository extends CassandraRepository<Ionosphere, Long> {

//    @AllowFiltering
//    <T> List<T> findByStationAndTimeStartGreaterThanEqualAndTimeEndLessThanEqual(Class<T> type, String station, LocalDateTime time_start, LocalDateTime time_end);

    @AllowFiltering
    List<Ionosphere> findByStationAndTimeStartGreaterThanEqualAndTimeEndLessThanEqual(String station, Instant time_start, Instant time_end);


//    @AllowFiltering
//    <T> List<T> findByTimeStartGreaterThanEqualAndTimeEndLessThanEqual(Class<T> type, LocalDateTime time_start, LocalDateTime time_end);


    @AllowFiltering
    List<Ionosphere> findByTimeStartGreaterThanEqualAndTimeEndLessThanEqual(Instant time_start, Instant time_end);

    @Query(allowFiltering = true, value = "SELECT * FROM ionosphere WHERE id = 901943132493;")
    Ionosphere getById();

    @Query(allowFiltering = true, value = "SELECT COUNT(*) FROM ionosphere;")
    Integer getCount();
}
