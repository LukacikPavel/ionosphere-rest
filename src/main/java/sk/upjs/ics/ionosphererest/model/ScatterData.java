package sk.upjs.ics.ionosphererest.model;

import java.time.Instant;

public class ScatterData extends Data{
    private boolean scintillation;

    public ScatterData(String station, Instant timeStart, Instant timeEnd, int azimuthStart, int azimuthEnd,
                       int elevationStart, int elevationEnd, boolean scintillation) {
        super(station, timeStart, timeEnd, azimuthStart, azimuthEnd, elevationStart, elevationEnd);
        this.scintillation = scintillation;
    }

    public ScatterData(Ionosphere ionosphere) {
        super(ionosphere);
    }

    public boolean isScintillation() {
        return scintillation;
    }

    public void setScintillation(boolean scintillation) {
        this.scintillation = scintillation;
    }
}
