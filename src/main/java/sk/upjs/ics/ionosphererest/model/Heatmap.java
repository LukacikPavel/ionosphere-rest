package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Heatmap extends Data {
    private int azimuthStart;
    private int azimuthEnd;
    private int elevationStart;
    private int elevationEnd;

    public Heatmap(){}

    public Heatmap(String station, Instant timeStart, Instant timeEnd,
                   int azimuthStart, int azimuthEnd, int elevationStart, int elevationEnd) {
        super(station, timeStart, timeEnd);
        this.azimuthStart = azimuthStart;
        this.azimuthEnd = azimuthEnd;
        this.elevationStart = elevationStart;
        this.elevationEnd = elevationEnd;
    }

    public Heatmap(Ionosphere ionosphere) {
        super(ionosphere);
        this.azimuthStart = ionosphere.getAzimuthStart();
        this.azimuthEnd = ionosphere.getAzimuthEnd();
        this.elevationStart = ionosphere.getElevationStart();
        this.elevationEnd = ionosphere.getElevationEnd();
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
        return "Heathmap{" +
                "azimuthStart=" + azimuthStart +
                ", azimuthEnd=" + azimuthEnd +
                ", elevationStart=" + elevationStart +
                ", elevationEnd=" + elevationEnd +
                '}';
    }
}
