package com.example.dusky.myapplication.backend.model.database.podujatie.podium;

import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityDescription;
import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dusky on 5/29/16.
 */
@Entity
@Table(name = PodiumRDBMSEntityModel.PodiumRDBMSEntityDescription.table)
public class PodiumRDBMSEntityModel extends PodujatieBaseRDBMSEntityModel {
    public static class PodiumRDBMSEntityDescription extends PodujatieBaseRDBMSEntityDescription {

        public static final String table = "podium_podium";

        public static final String columnMapaId = "mapa_id";
        public static final String columnNazov = "nazov";
        public static final String columnLongitude = "longitude";
        public static final String columnLatidute = "latidute";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static PodiumRDBMSEntityDescription entityDescription = new PodiumRDBMSEntityDescription();

    @Column(name = PodiumRDBMSEntityDescription.columnMapaId)
    protected Long mapaId;

    @Column(name = PodiumRDBMSEntityDescription.columnNazov)
    protected String nazov;

    @Column(name = PodiumRDBMSEntityDescription.columnLongitude)
    protected Double longitude;

    @Column(name = PodiumRDBMSEntityDescription.columnLatidute)
    protected Double latidute;

    public static PodiumRDBMSEntityDescription getEntityDescription() {
        return entityDescription;
    }

    public static void setEntityDescription(PodiumRDBMSEntityDescription entityDescription) {
        PodiumRDBMSEntityModel.entityDescription = entityDescription;
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
        return "PodiumRDBMSEntityModel{" +
                "mapaId=" + mapaId +
                ", nazov='" + nazov + '\'' +
                ", longitude=" + longitude +
                ", latidute=" + latidute +
                '}';
    }
}
