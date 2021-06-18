package sk.upjs.ics.ionosphererest.model;

import java.util.Objects;

public class AzimuthElevation {
    private int azimuthStart;
    private int azimuthEnd;
    private int elevationStart;
    private int elevationEnd;

    public AzimuthElevation() {}

    public AzimuthElevation(int azimuthStart, int azimuthEnd, int elevationStart, int elevationEnd) {
        this.azimuthStart = azimuthStart;
        this.azimuthEnd = azimuthEnd;
        this.elevationStart = elevationStart;
        this.elevationEnd = elevationEnd;
    }

    public AzimuthElevation(Ionosphere ionosphere) {
        this.azimuthStart = ionosphere.getAzimuthStart();
        this.azimuthEnd = ionosphere.getAzimuthEnd();
        this.elevationStart = ionosphere.getElevationStart();
        this.elevationEnd = ionosphere.getElevationEnd();
    }

    public AzimuthElevation(Data data) {
        this.azimuthStart = data.getAzimuthStart();
        this.azimuthEnd = data.getAzimuthEnd();
        this.elevationStart = data.getElevationStart();
        this.elevationEnd = data.getElevationEnd();
    }

    public int getAzimuthStart() {
        return azimuthStart;
    }

    public void setAzimuthStart(int azimuthStart) {
        this.azimuthStart = azimuthStart;
    }

    public int getAzimuthEnd() {
        return azimuthEnd;
    }

    public void setAzimuthEnd(int azimuthEnd) {
        this.azimuthEnd = azimuthEnd;
    }

    public int getElevationStart() {
        return elevationStart;
    }

    public void setElevationStart(int elevationStart) {
        this.elevationStart = elevationStart;
    }

    public int getElevationEnd() {
        return elevationEnd;
    }

    public void setElevationEnd(int elevationEnd) {
        this.elevationEnd = elevationEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AzimuthElevation that = (AzimuthElevation) o;
        return azimuthStart == that.azimuthStart && azimuthEnd == that.azimuthEnd && elevationStart == that.elevationStart && elevationEnd == that.elevationEnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(azimuthStart, azimuthEnd, elevationStart, elevationEnd);
    }
}
