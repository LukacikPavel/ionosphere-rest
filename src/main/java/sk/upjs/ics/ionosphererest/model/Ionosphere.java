package sk.upjs.ics.ionosphererest.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table
public class Ionosphere {

    @PrimaryKey
    private Long id;
    private String station;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private Integer azimuthStart;
    private Integer azimuthEnd;
    private Integer elevationStart;
    private Integer elevationEnd;
    private Double tecu;
    private Double s4;
    private Double sigmaphi;

    public Ionosphere(){}

    public Ionosphere(Long id, String station, LocalDateTime timeStart, LocalDateTime timeEnd, Integer azimuthStart,
                      Integer azimuthEnd, Integer elevationStart, Integer elevationEnd, Double tecu, Double s4,
                      Double sigmaphi) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
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
                "id=" + id +
                ", station='" + station + '\'' +
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
