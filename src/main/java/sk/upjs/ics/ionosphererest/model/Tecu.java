package sk.upjs.ics.ionosphererest.model;

public interface Tecu {
    String getStation();
    Integer getAzimuthStart();
    Integer getAzimuthEnd();
    Integer getElevationStart();
    Integer getElevationEnd();
    Double getTecu();
}
