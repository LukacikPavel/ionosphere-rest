package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class ScatterTecu extends ScatterData {

    private double tecu;

    public ScatterTecu(String station, Instant timeStart, Instant timeEnd, int azimuthStart, int azimuthEnd,
                       int elevationStart, int elevationEnd, boolean scintillation, double tecu) {
        super(station, timeStart, timeEnd, azimuthStart, azimuthEnd, elevationStart, elevationEnd, scintillation);
        this.tecu = tecu;
    }

    public ScatterTecu(Ionosphere ionosphere) {
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
        return "ScatterTecu{" +
                "tecu=" + tecu +
                '}';
    }
}
