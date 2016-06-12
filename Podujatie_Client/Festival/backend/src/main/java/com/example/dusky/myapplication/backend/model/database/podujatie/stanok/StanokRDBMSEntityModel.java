package com.example.dusky.myapplication.backend.model.database.podujatie.stanok;

import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityDescription;
import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dusky on 5/29/16.
 */
@Entity
@Table(name = StanokRDBMSEntityModel.StanokRDBMSEntityDescription.table)
public class StanokRDBMSEntityModel extends PodujatieBaseRDBMSEntityModel {

    public static class StanokRDBMSEntityDescription extends PodujatieBaseRDBMSEntityDescription {

        public static final String table = "stanok_stanok";

        public static final String columnMapaId = "mapa_id";
        public static final String columnNazov = "nazov";
        public static final String columnLongitude = "longitude";
        public static final String columnLatidute = "latidute";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static StanokRDBMSEntityDescription entityDescription = new StanokRDBMSEntityDescription();

    @Column(name = StanokRDBMSEntityDescription.columnMapaId)
    protected Long mapaId;

    @Column(name = StanokRDBMSEntityDescription.columnNazov)
    protected String nazov;

    @Column(name = StanokRDBMSEntityDescription.columnLongitude)
    protected Double longitude;

    @Column(name = StanokRDBMSEntityDescription.columnLatidute)
    protected Double latidute;

    public static StanokRDBMSEntityDescription getEntityDescription() {
        return entityDescription;
    }

    public static void setEntityDescription(StanokRDBMSEntityDescription entityDescription) {
        StanokRDBMSEntityModel.entityDescription = entityDescription;
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
        return "StanokRDBMSEntityModel{" +
                "mapaId=" + mapaId +
                ", nazov='" + nazov + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latidute='" + latidute + '\'' +
                '}';
    }
}
