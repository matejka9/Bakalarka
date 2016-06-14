package com.example.dusky.myapplication.backend.model.database.podujatie.notifikacia;

import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityDescription;
import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dusky on 6/14/16.
 */
@Entity
@Table(name = NotifikaciaRDBMSEntityModel.NotifikaciaRDBMSEntityDescription.table)
public class NotifikaciaRDBMSEntityModel extends PodujatieBaseRDBMSEntityModel {

    public static class NotifikaciaRDBMSEntityDescription extends PodujatieBaseRDBMSEntityDescription {

        public static final String table = "notifikacia_notifikacia";

        public static final String columnMapaId = "mapa_id";
        public static final String columnNadpis = "nadpis";
        public static final String columnText = "text";
        public static final String columnDetail = "detail";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static NotifikaciaRDBMSEntityDescription entityDescription = new NotifikaciaRDBMSEntityDescription();

    @Column(name = NotifikaciaRDBMSEntityDescription.columnMapaId)
    protected Long mapaId;

    @Column(name = NotifikaciaRDBMSEntityDescription.columnNadpis)
    protected String nadpis;

    @Column(name = NotifikaciaRDBMSEntityDescription.columnText)
    protected String text;

    @Column(name = NotifikaciaRDBMSEntityDescription.columnDetail)
    protected String detail;

    public static NotifikaciaRDBMSEntityDescription getEntityDescription() {
        return entityDescription;
    }

    public static void setEntityDescription(NotifikaciaRDBMSEntityDescription entityDescription) {
        NotifikaciaRDBMSEntityModel.entityDescription = entityDescription;
    }

    public Long getMapaId() {
        return mapaId;
    }

    public void setMapaId(Long mapaId) {
        this.mapaId = mapaId;
    }

    public String getNadpis() {
        return nadpis;
    }

    public void setNadpis(String nadpis) {
        this.nadpis = nadpis;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "NotifikaciaRDBMSEntityModel{" +
                "mapaId=" + mapaId +
                ", nadpis='" + nadpis + '\'' +
                ", text='" + text + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
