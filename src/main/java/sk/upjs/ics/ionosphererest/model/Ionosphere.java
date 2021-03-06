package sk.upjs.ics.ionosphererest.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;

@Table
public class Ionosphere {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private String station;
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private Instant timeStart;
    private Instant timeEnd;
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private Integer azimuthStart;
    private Integer azimuthEnd;
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED)
    private Integer elevationStart;
    private Integer elevationEnd;
    private Double tecu;
    private Double s4;
    private Double sigmaphi;

    public Ionosphere(){}

    public Ionosphere(String station, Instant timeStart, Instant timeEnd, Integer azimuthStart, Integer azimuthEnd,
                      Integer elevationStart, Integer elevationEnd, Double tecu, Double s4, Double sigmaphi) {
        this.station = station;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.azimuthStart = azimuthStart;
        this.azimuthEnd = azimuthEnd;
        this.elevationStart = elevationStart;
        this.elevationEnd = elevationEnd;
        this.tecu = tecu;
        this.s4 = s4;
        this.sigmaphi = sigmaphi;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Instant getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Instant timeStart) {
        this.timeStart = timeStart;
    }

    public Instant getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Instant timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Integer getAzimuthStart() {
        return azimuthStart;
    }

    public void setAzimuthStart(Integer azimuthStart) {
        this.azimuthStart = azimuthStart;
    }

    public Integer getAzimuthEnd() {
        return azimuthEnd;
    }

    public void setAzimuthEnd(Integer azimuthEnd) {
        this.azimuthEnd = azimuthEnd;
    }

    public Integer getElevationStart() {
        return elevationStart;
    }

    public void setElevationStart(Integer elevationStart) {
        this.elevationStart = elevationStart;
    }

    public Integer getElevationEnd() {
        return elevationEnd;
    }

    public void setElevationEnd(Integer elevationEnd) {
        this.elevationEnd = elevationEnd;
    }

    public Double getTecu() {
        return tecu;
    }

    public void setTecu(Double tecu) {
        this.tecu = tecu;
    }

    public Double getS4() {
        return s4;
    }

    public void setS4(Double s4) {
        this.s4 = s4;
    }

    public Double getSigmaphi() {
        return sigmaphi;
    }

    public void setSigmaphi(Double sigmaphi) {
        this.sigmaphi = sigmaphi;
    }

    @Override
    public String toString() {
        return "Ionosphere{" +
                "station='" + station + '\'' +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", azimuthStart=" + azimuthStart +
                ", azimuthEnd=" + azimuthEnd +
                ", elevationStart=" + elevationStart +
                ", elevationEnd=" + elevationEnd +
                ", tecu=" + tecu +
                ", s4=" + s4 +
                ", sigmaphi=" + sigmaphi +
                '}';
    }
}
