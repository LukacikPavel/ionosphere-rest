package sk.upjs.ics.ionosphererest.model;

import java.time.LocalDateTime;

public class HeatmapTecu extends Heatmap{

    private double tecu;

    public HeatmapTecu(){}

    public HeatmapTecu(String station, LocalDateTime timeStart, LocalDateTime timeEnd, int azimuthStart, int azimuthEnd,
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
