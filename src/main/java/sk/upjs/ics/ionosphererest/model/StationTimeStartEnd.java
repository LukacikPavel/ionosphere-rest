package sk.upjs.ics.ionosphererest.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class StationTimeStartEnd {

    private String station;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    public StationTimeStartEnd(){}

    public StationTimeStartEnd(String station, LocalDateTime timeStart, LocalDateTime timeEnd) {
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

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
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
