package com.example.dusky.myapplication.backend.model.endpoint.podujatie.mapa;

import com.example.dusky.myapplication.backend.model.database.podujatie.mapa.MapaRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.BaseEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

/**
 * Created by dusky on 5/29/16.
 */
public class MapaResponseEntityModel extends BaseEntityModel{

    @ApiResourceProperty(name = "podujatie_id")
    protected Long podujatieId;

    @ApiResourceProperty(name = "mapka_url")
    protected String mapkaUrl;

    @ApiResourceProperty(name = "lavy_horny_roh_gps_latitude")
    protected Double lavyHornyRohGpsLatitude;

    @ApiResourceProperty(name = "lavy_horny_roh_gps_longitude")
    protected Double lavyHornyRohGpsLongitude;

    @ApiResourceProperty(name = "pravy_spodny_roh_gps_latitude")
    protected Double pravySpodnyRohGpsLatitude;

    @ApiResourceProperty(name = "pravy_spodny_roh_gps_longitude")
    protected Double pravySpodnyRohGpsLongitude;

    public MapaResponseEntityModel(MapaRDBMSEntityModel mapaDb) {
        super(mapaDb);
        this.podujatieId = mapaDb.getPodujatieId();
        this.mapkaUrl = mapaDb.getMapka();
        this.lavyHornyRohGpsLatitude = mapaDb.getLavyHornyRohGpsLatitude();
        this.lavyHornyRohGpsLongitude = mapaDb.getLavyHornyRohGpsLongitude();
        this.pravySpodnyRohGpsLatitude = mapaDb.getPravSpodnyRohGpsLatitude();
        this.pravySpodnyRohGpsLongitude = mapaDb.getPravySpodnyRohGpsLongitude();
    }


    public Long getPodujatieId() {
        return podujatieId;
    }

    public void setPodujatieId(Long podujatieId) {
        this.podujatieId = podujatieId;
    }

    public String getMapkaUrl() {
        return mapkaUrl;
    }

    public void setMapkaUrl(String mapkaUrl) {
        this.mapkaUrl = mapkaUrl;
    }

    public Double getLavyHornyRohGpsLatitude() {
        return lavyHornyRohGpsLatitude;
    }

    public void setLavyHornyRohGpsLatitude(Double lavyHornyRohGpsLatitude) {
        this.lavyHornyRohGpsLatitude = lavyHornyRohGpsLatitude;
    }

    public Double getLavyHornyRohGpsLongitude() {
        return lavyHornyRohGpsLongitude;
    }

    public void setLavyHornyRohGpsLongitude(Double lavyHornyRohGpsLongitude) {
        this.lavyHornyRohGpsLongitude = lavyHornyRohGpsLongitude;
    }

    public Double getPravySpodnyRohGpsLatitude() {
        return pravySpodnyRohGpsLatitude;
    }

    public void setPravySpodnyRohGpsLatitude(Double pravySpodnyRohGpsLatitude) {
        this.pravySpodnyRohGpsLatitude = pravySpodnyRohGpsLatitude;
    }

    public Double getPravySpodnyRohGpsLongitude() {
        return pravySpodnyRohGpsLongitude;
    }

    public void setPravySpodnyRohGpsLongitude(Double pravySpodnyRohGpsLongitude) {
        this.pravySpodnyRohGpsLongitude = pravySpodnyRohGpsLongitude;
    }

    @Override
    public String toString() {
        return "MapaResponseEntityModel{" +
                "podujatieId=" + podujatieId +
                ", mapkaUrl='" + mapkaUrl + '\'' +
                ", lavyHornyRohGpsLatitude='" + lavyHornyRohGpsLatitude + '\'' +
                ", lavyHornyRohGpsLongitude='" + lavyHornyRohGpsLongitude + '\'' +
                ", pravySpodnyRohGpsLatitude='" + pravySpodnyRohGpsLatitude + '\'' +
                ", pravySpodnyRohGpsLongitude='" + pravySpodnyRohGpsLongitude + '\'' +
                '}';
    }
}
