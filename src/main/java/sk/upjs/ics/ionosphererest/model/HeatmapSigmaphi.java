package sk.upjs.ics.ionosphererest.model;

import java.time.LocalDateTime;

public class HeatmapSigmaphi extends Heatmap {

    private double sigmaphi;

    public HeatmapSigmaphi(){}

    public HeatmapSigmaphi(String station, LocalDateTime timeStart, LocalDateTime timeEnd, int azimuthStart, int azimuthEnd,
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
