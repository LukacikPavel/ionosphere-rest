package sk.upjs.ics.ionosphererest.model;

import java.time.LocalDateTime;

public class ScatterTecu extends Data {

    private double tecu;

    public ScatterTecu(){}

    public ScatterTecu(String station, LocalDateTime timeStart, LocalDateTime timeEnd, double tecu) {
        super(station, timeStart, timeEnd);
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
