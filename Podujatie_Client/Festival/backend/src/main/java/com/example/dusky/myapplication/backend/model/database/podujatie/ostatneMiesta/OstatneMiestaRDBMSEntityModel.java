package com.example.dusky.myapplication.backend.model.database.podujatie.ostatneMiesta;

import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityDescription;
import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dusky on 5/29/16.
 */
@Entity
@Table(name = OstatneMiestaRDBMSEntityModel.OstatneMiestaRDBMSEntityDescription.table)
public class OstatneMiestaRDBMSEntityModel extends PodujatieBaseRDBMSEntityModel {

    public static class OstatneMiestaRDBMSEntityDescription extends PodujatieBaseRDBMSEntityDescription {

        public static final String table = "nove_miesto_novemiesto";

        public static final String columnMapaId = "mapa_id";
        public static final String columnNazov = "nazov";
        public static final String columnDetail = "detail";
        public static final String columnLongitude = "longitude";
        public static final String columnLatidute = "latidute";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static OstatneMiestaRDBMSEntityDescription entityDescription = new OstatneMiestaRDBMSEntityDescription();

    @Column(name = OstatneMiestaRDBMSEntityDescription.columnMapaId)
    protected Long mapaId;

    @Column(name = OstatneMiestaRDBMSEntityDescription.columnNazov)
    protected String nazov;

    @Column(name = OstatneMiestaRDBMSEntityDescription.columnDetail)
    protected String detail;

    @Column(name = OstatneMiestaRDBMSEntityDescription.columnLongitude)
    protected Double longitude;

    @Column(name = OstatneMiestaRDBMSEntityDescription.columnLatidute)
    protected Double latidute;

    public static OstatneMiestaRDBMSEntityDescription getEntityDescription() {
        return entityDescription;
    }

    public static void setEntityDescription(OstatneMiestaRDBMSEntityDescription entityDescription) {
        OstatneMiestaRDBMSEntityModel.entityDescription = entityDescription;
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
        return "OstatneMiestaRDBMSEntityModel{" +
                "mapaId=" + mapaId +
                ", nazov='" + nazov + '\'' +
                ", longitude=" + longitude +
                ", latidute=" + latidute +
                '}';
    }
}
