package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class HeatmapTecu extends Data{

    private double tecu;

    public HeatmapTecu(){}

    public HeatmapTecu(String station, Instant timeStart, Instant timeEnd, int azimuthStart, int azimuthEnd,
                       int elevationStart, int elevationEnd, double tecu) {
        super(station, timeStart, timeEnd, azimuthStart, azimuthEnd, elevationStart, elevationEnd);
        this.tecu = tecu;
    }

    public HeatmapTecu(Ionosphere ionosphere) {
        super(ionosphere);
        this.tecu = ionosphere.getTecu();
    }

    public double getTecu() {
        return tecu;
    }

    public void setTecu(double tecu) {
        this.tecu = tecu;
    }

    @Override
    public String toString() {
        return "DataTecu{" +
                "tecu=" + tecu +
                '}';
    }
}
