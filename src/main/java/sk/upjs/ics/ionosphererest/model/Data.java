package sk.upjs.ics.ionosphererest.model;

import java.time.LocalDateTime;

public class Data {
    private String station;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    public Data(){}

    public Data(String station, LocalDateTime timeStart, LocalDateTime timeEnd) {
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
    public String toString() {
        return "Data{" +
                "station='" + station + '\'' +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }
}
