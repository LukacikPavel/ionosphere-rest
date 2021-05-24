package sk.upjs.ics.ionosphererest.model;

public interface Sigmaphi {
    String getStation();
    Integer getAzimuthStart();
    Integer getAzimuthEnd();
    Integer getElevationStart();
    Integer getElevationEnd();
    Double getSigmaphi();
}
