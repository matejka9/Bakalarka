package com.example.dusky.myapplication.backend.model.endpoint.podujatie.ostatneMiesta;

import com.example.dusky.myapplication.backend.model.database.podujatie.ostatneMiesta.OstatneMiestaRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.BaseEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

/**
 * Created by dusky on 5/29/16.
 */
public class OstatneMiestaResponseEntityModel extends BaseEntityModel {
    @ApiResourceProperty(name = "mapa_id")
    protected Long mapaId;

    @ApiResourceProperty(name = "nazov")
    protected String nazov;

    @ApiResourceProperty(name = "detail")
    protected String detail;

    @ApiResourceProperty(name = "longitude")
    protected Double longitude;

    @ApiResourceProperty(name = "latidute")
    protected Double latidute;

    public OstatneMiestaResponseEntityModel(OstatneMiestaRDBMSEntityModel ostatneMiestoRDBMS) {
        super(ostatneMiestoRDBMS);
        this.mapaId = ostatneMiestoRDBMS.getMapaId();
        this.nazov = ostatneMiestoRDBMS.getNazov();
        this.detail = ostatneMiestoRDBMS.getDetail();
        this.longitude = ostatneMiestoRDBMS.getLongitude();
        this.latidute = ostatneMiestoRDBMS.getLatidute();
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
                ", detail='" + detail + '\'' +
                ", longitude=" + longitude +
                ", latidute=" + latidute +
                '}';
    }
}
