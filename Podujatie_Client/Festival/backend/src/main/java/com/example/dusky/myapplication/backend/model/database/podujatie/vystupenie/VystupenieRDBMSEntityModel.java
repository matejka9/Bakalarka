package com.example.dusky.myapplication.backend.model.database.podujatie.vystupenie;

import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityDescription;
import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dusky on 5/29/16.
 */
@Entity
@Table(name = VystupenieRDBMSEntityModel.VystupeniePodiaRDBMSEntityDescription.table)
public class VystupenieRDBMSEntityModel extends PodujatieBaseRDBMSEntityModel {

    public static class VystupeniePodiaRDBMSEntityDescription extends PodujatieBaseRDBMSEntityDescription {

        public static final String table = "vystupenie_podia_vystupeniepodia";

        public static final String columnVystupenieId = "vystupenie_id";
        public static final String columnPodiumId = "podium_id";
        public static final String columnDetail = "detail";
        public static final String columnCasOd = "cas_od";
        public static final String columnCasDo = "cas_do";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static VystupeniePodiaRDBMSEntityDescription entityDescriptionVystupeniePodia = new VystupeniePodiaRDBMSEntityDescription();

    public static class VystupenieRDBMSEntityDescription extends PodujatieBaseRDBMSEntityDescription {

        public static final String table = "vystupenie_vystupenie";

        public static final String columnNazov = "nazov";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static VystupenieRDBMSEntityDescription entityDescriptionVystupenie = new VystupenieRDBMSEntityDescription();

    @Column(name = VystupeniePodiaRDBMSEntityDescription.columnVystupenieId)
    protected Long vystupenieId;

    @Column(name = VystupeniePodiaRDBMSEntityDescription.columnPodiumId)
    protected Long podiumId;

    @Column(name = VystupeniePodiaRDBMSEntityDescription.columnDetail)
    protected String detail;

    @Column(name = VystupeniePodiaRDBMSEntityDescription.columnCasOd)
    protected Date casOd;

    @Column(name = VystupeniePodiaRDBMSEntityDescription.columnCasDo)
    protected Date casDo;

    @Column(name = VystupenieRDBMSEntityDescription.columnNazov)
    protected String nazov;

    public static VystupeniePodiaRDBMSEntityDescription getEntityDescriptionVystupeniePodia() {
        return entityDescriptionVystupeniePodia;
    }

    public static void setEntityDescriptionVystupeniePodia(VystupeniePodiaRDBMSEntityDescription entityDescriptionVystupeniePodia) {
        VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia = entityDescriptionVystupeniePodia;
    }

    public static VystupenieRDBMSEntityDescription getEntityDescriptionVystupenie() {
        return entityDescriptionVystupenie;
    }

    public static void setEntityDescriptionVystupenie(VystupenieRDBMSEntityDescription entityDescriptionVystupenie) {
        VystupenieRDBMSEntityModel.entityDescriptionVystupenie = entityDescriptionVystupenie;
    }

    public Long getVystupenieId() {
        return vystupenieId;
    }

    public void setVystupenieId(Long vystupenieId) {
        this.vystupenieId = vystupenieId;
    }

    public Long getPodiumId() {
        return podiumId;
    }

    public void setPodiumId(Long podiumId) {
        this.podiumId = podiumId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCasOd() {
        return casOd;
    }

    public void setCasOd(Date casOd) {
        this.casOd = casOd;
    }

    public Date getCasDo() {
        return casDo;
    }

    public void setCasDo(Date casDo) {
        this.casDo = casDo;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String toString() {
        return "VystupenieRDBMSEntityModel{" +
                "vystupenieId=" + vystupenieId +
                ", podiumId=" + podiumId +
                ", detail='" + detail + '\'' +
                ", casOd=" + casOd +
                ", casDo=" + casDo +
                ", nazov='" + nazov + '\'' +
                '}';
    }
}
