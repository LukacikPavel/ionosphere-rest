package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Data {
    private String station;
    private Instant timeStart;
    private Instant timeEnd;
    private int azimuthStart;
    private int azimuthEnd;
    private int elevationStart;
    private int elevationEnd;

    public Data(){}

    public Data(String station, Instant timeStart, Instant timeEnd, int azimuthStart, int azimuthEnd,
                int elevationStart, int elevationEnd) {
        this.station = station;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.azimuthStart = azimuthStart;
        this.azimuthEnd = azimuthEnd;
        this.elevationStart = elevationStart;
        this.elevationEnd = elevationEnd;
    }

    public Data(Ionosphere ionosphere) {
        this.station = ionosphere.getStation();
        this.timeStart = ionosphere.getTimeStart();
        this.timeEnd = ionosphere.getTimeEnd();
        this.azimuthStart = ionosphere.getAzimuthStart();
        this.azimuthEnd = ionosphere.getAzimuthEnd();
        this.elevationStart = ionosphere.getElevationStart();
        this.elevationEnd = ionosphere.getElevationEnd();
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

    public int getAzimuthStart() {
        return azimuthStart;
    }

    public void setAzimuthStart(int azimuthStart) {
        this.azimuthStart = azimuthStart;
    }

    public int getAzimuthEnd() {
        return azimuthEnd;
    }

    public void setAzimuthEnd(int azimuthEnd) {
        this.azimuthEnd = azimuthEnd;
    }

    public int getElevationStart() {
        return elevationStart;
    }

    public void setElevationStart(int elevationStart) {
        this.elevationStart = elevationStart;
    }

    public int getElevationEnd() {
        return elevationEnd;
    }

    public void setElevationEnd(int elevationEnd) {
        this.elevationEnd = elevationEnd;
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
