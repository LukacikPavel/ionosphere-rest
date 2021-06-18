package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class HeatmapSigmaphi extends Data {

    private double sigmaphi;

    public HeatmapSigmaphi(){}

    public HeatmapSigmaphi(String station, Instant timeStart, Instant timeEnd, int azimuthStart, int azimuthEnd,
                           int elevationStart, int elevationEnd, double sigmaphi) {
        super(station, timeStart, timeEnd, azimuthStart, azimuthEnd, elevationStart, elevationEnd);
        this.sigmaphi = sigmaphi;
    }

    public HeatmapSigmaphi(Ionosphere ionosphere) {
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
        return "DataSigmaphi{" +
                "sigmaphi=" + sigmaphi +
                '}';
    }
}
