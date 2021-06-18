package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class ScatterS4 extends ScatterData{

    private double s4;

    public ScatterS4(String station, Instant timeStart, Instant timeEnd, int azimuthStart, int azimuthEnd,
                     int elevationStart, int elevationEnd, boolean scintillation, double s4) {
        super(station, timeStart, timeEnd, azimuthStart, azimuthEnd, elevationStart, elevationEnd, scintillation);
        this.s4 = s4;
    }

    public ScatterS4(Ionosphere ionosphere) {
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
        return "ScatterS4{" +
                "s4=" + s4 +
                '}';
    }
}
