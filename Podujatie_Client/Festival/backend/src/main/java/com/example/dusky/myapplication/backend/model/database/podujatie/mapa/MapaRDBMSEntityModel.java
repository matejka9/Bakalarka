package com.example.dusky.myapplication.backend.model.database.podujatie.mapa;

import com.example.dusky.myapplication.backend.model.database.BaseRDBMSEntityDescription;
import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityDescription;
import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dusky on 5/29/16.
 */
@Entity
@Table(name = MapaRDBMSEntityModel.MapaRDBMSEntityDescription.table)
public class MapaRDBMSEntityModel extends PodujatieBaseRDBMSEntityModel {

    public static class MapaRDBMSEntityDescription extends PodujatieBaseRDBMSEntityDescription {

        public static final String table = "mapa_mapa";

        public static final String columnPodujatieId = "podujatie_id";
        public static final String columnMapka = "mapka";
        public static final String columnLavyHornyRohGpsLatitude = "lavy_horny_roh_gps_latitude";
        public static final String columnLavyHornyRohGpsLongitude = "lavy_horny_roh_gps_longitude";
        public static final String columnPravSpodnyRohGpsLatitude = "pravy_spodny_roh_gps_latitude";
        public static final String columnPravySpodnyRohGpsLongitude = "pravy_spodny_roh_gps_longitude";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static MapaRDBMSEntityDescription entityDescription = new MapaRDBMSEntityDescription();

    @Column(name = MapaRDBMSEntityDescription.columnPodujatieId)
    protected Long podujatieId;

    @Column(name = MapaRDBMSEntityDescription.columnMapka)
    protected String mapka;

    @Column(name = MapaRDBMSEntityDescription.columnLavyHornyRohGpsLatitude)
    protected Double lavyHornyRohGpsLatitude;

    @Column(name = MapaRDBMSEntityDescription.columnLavyHornyRohGpsLongitude)
    protected Double lavyHornyRohGpsLongitude;

    @Column(name = MapaRDBMSEntityDescription.columnPravSpodnyRohGpsLatitude)
    protected Double pravSpodnyRohGpsLatitude;

    @Column(name = MapaRDBMSEntityDescription.columnPravySpodnyRohGpsLongitude)
    protected Double pravySpodnyRohGpsLongitude;

    public static MapaRDBMSEntityDescription getEntityDescription() {
        return entityDescription;
    }

    public static void setEntityDescription(MapaRDBMSEntityDescription entityDescription) {
        MapaRDBMSEntityModel.entityDescription = entityDescription;
    }

    public Long getPodujatieId() {
        return podujatieId;
    }

    public void setPodujatieId(Long podujatieId) {
        this.podujatieId = podujatieId;
    }

    public String getMapka() {
        return mapka;
    }

    public void setMapka(String mapka) {
        this.mapka = mapka;
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

    public Double getPravSpodnyRohGpsLatitude() {
        return pravSpodnyRohGpsLatitude;
    }

    public void setPravSpodnyRohGpsLatitude(Double pravSpodnyRohGpsLatitude) {
        this.pravSpodnyRohGpsLatitude = pravSpodnyRohGpsLatitude;
    }

    public Double getPravySpodnyRohGpsLongitude() {
        return pravySpodnyRohGpsLongitude;
    }

    public void setPravySpodnyRohGpsLongitude(Double pravySpodnyRohGpsLongitude) {
        this.pravySpodnyRohGpsLongitude = pravySpodnyRohGpsLongitude;
    }

    @Override
    public String toString() {
        return "MapaRDBMSEntityModel{" +
                "podujatieId=" + podujatieId +
                ", mapka='" + mapka + '\'' +
                ", lavyHornyRohGpsLatitude=" + lavyHornyRohGpsLatitude +
                ", lavyHornyRohGpsLongitude=" + lavyHornyRohGpsLongitude +
                ", pravySpodnyRohGpsLatitude=" + pravSpodnyRohGpsLatitude +
                ", pravySpodnyRohGpsLongitude=" + pravySpodnyRohGpsLongitude +
                '}';
    }
}
