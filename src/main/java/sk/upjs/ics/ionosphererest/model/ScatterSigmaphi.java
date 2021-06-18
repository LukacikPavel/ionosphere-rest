package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class ScatterSigmaphi extends ScatterData {

    private double sigmaphi;

    public ScatterSigmaphi(String station, Instant timeStart, Instant timeEnd, int azimuthStart, int azimuthEnd,
                           int elevationStart, int elevationEnd, boolean scintillation, double sigmaphi) {
        super(station, timeStart, timeEnd, azimuthStart, azimuthEnd, elevationStart, elevationEnd, scintillation);
        this.sigmaphi = sigmaphi;
    }

    public ScatterSigmaphi(Ionosphere ionosphere) {
        super(ionosphere);
        this.sigmaphi = ionosphere.getSigmaphi();
    }

    public double getSigmaphi() {
        return sigmaphi;
    }

    public void setSigmaphi(double sigmaphi) {
        this.sigmaphi = sigmaphi;
    }

    @Override
    public String toString() {
        return "ScatterSigmaphi{" +
                "sigmaphi=" + sigmaphi +
                '}';
    }
}
