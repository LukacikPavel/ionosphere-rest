package sk.upjs.ics.ionosphererest.model;

import java.time.LocalDateTime;

public class ScatterSigmaphi extends Data {

    private double sigmaphi;

    public ScatterSigmaphi(){}

    public ScatterSigmaphi(String station, LocalDateTime timeStart, LocalDateTime timeEnd, double sigmaphi) {
        super(station, timeStart, timeEnd);
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
