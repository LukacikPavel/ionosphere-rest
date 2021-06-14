package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class StationTimeStartEnd {

    private String station;
    private Instant timeStart;
    private Instant timeEnd;

    public StationTimeStartEnd(){}

    public StationTimeStartEnd(String station, Instant timeStart, Instant timeEnd) {
        this.station = station;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public StationTimeStartEnd(Ionosphere ionosphere){
        this.station = ionosphere.getStation();
        this.timeStart = ionosphere.getTimeStart();
        this.timeEnd = ionosphere.getTimeEnd();
    }

    public StationTimeStartEnd(Data data){
        this.station = data.getStation();
        this.timeStart = data.getTimeStart();
        this.timeEnd = data.getTimeEnd();
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Instant getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Instant timeStart) {
        this.timeStart = timeStart;
    }

    public Instant getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationTimeStartEnd that = (StationTimeStartEnd) o;
        return getStation().equals(that.getStation()) && getTimeStart().equals(that.getTimeStart()) && getTimeEnd().equals(that.getTimeEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStation(), getTimeStart(), getTimeEnd());
    }
}
