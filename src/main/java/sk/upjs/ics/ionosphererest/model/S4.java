package sk.upjs.ics.ionosphererest.model;

public interface S4 {
    String getStation();
    Integer getAzimuthStart();
    Integer getAzimuthEnd();
    Integer getElevationStart();
    Integer getElevationEnd();
    Double getS4();
}
