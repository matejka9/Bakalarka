package com.example.dusky.myapplication.backend.model.endpoint.podujatie.podium;

import com.example.dusky.myapplication.backend.model.database.podujatie.podium.PodiumRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.BaseEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

/**
 * Created by dusky on 2/14/16.
 */
public class PodiumResponseEntityModel extends BaseEntityModel {

    @ApiResourceProperty(name = "mapa_id")
    protected Long mapaId;

    @ApiResourceProperty(name = "nazov")
    protected String nazov;

    @ApiResourceProperty(name = "longitude")
    protected Double longitude;

    @ApiResourceProperty(name = "latidute")
    protected Double latidute;

    public PodiumResponseEntityModel(PodiumRDBMSEntityModel podiumRDBMS) {
        super(podiumRDBMS);
        this.mapaId = podiumRDBMS.getMapaId();
        this.nazov = podiumRDBMS.getNazov();
        this.longitude = podiumRDBMS.getLongitude();
        this.latidute = podiumRDBMS.getLatidute();
    }

    public Long getMapaId() {
        return mapaId;
    }

    public void setMapaId(Long mapaId) {
        this.mapaId = mapaId;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatidute() {
        return latidute;
    }

    public void setLatidute(Double latidute) {
        this.latidute = latidute;
    }

    @Override
    public String toString() {
        return "PodiumResponseEntityModel{" +
                "mapaId=" + mapaId +
                ", nazov='" + nazov + '\'' +
                ", longitude=" + longitude +
                ", latidute=" + latidute +
                '}';
    }
}
