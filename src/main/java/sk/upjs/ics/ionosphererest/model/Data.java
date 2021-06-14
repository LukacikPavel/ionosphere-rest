package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Data {
    private String station;
    private Instant timeStart;
    private Instant timeEnd;

    public Data(){}

    public Data(String station, Instant timeStart, Instant timeEnd) {
        this.station = station;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Data(Ionosphere ionosphere) {
        this.station = ionosphere.getStation();
        this.timeStart = ionosphere.getTimeStart();
        this.timeEnd = ionosphere.getTimeEnd();
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
    public String toString() {
        return "Data{" +
                "station='" + station + '\'' +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }
}
