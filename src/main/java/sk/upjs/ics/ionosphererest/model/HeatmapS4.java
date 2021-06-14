package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class HeatmapS4 extends Heatmap {

    private double s4;

    public HeatmapS4(){}

    public HeatmapS4(String station, Instant timeStart, Instant timeEnd,
                     int azimuthStart, int azimuthEnd, int elevationStart, int elevationEnd, double s4) {
        super(station, timeStart, timeEnd, azimuthStart, azimuthEnd, elevationStart, elevationEnd);
        this.s4 = s4;
    }

    public HeatmapS4(Ionosphere ionosphere) {
        super(ionosphere);
        this.s4 = ionosphere.getS4();
    }

    public double getS4() {
        return s4;
    }

    public void setS4(double s4) {
        this.s4 = s4;
    }

    @Override
    public String toString() {
        return "DataS4{" +
                "s4=" + s4 +
                '}';
    }
}
