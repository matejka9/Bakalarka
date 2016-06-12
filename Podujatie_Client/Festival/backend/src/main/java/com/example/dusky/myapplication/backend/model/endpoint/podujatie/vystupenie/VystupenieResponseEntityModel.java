package com.example.dusky.myapplication.backend.model.endpoint.podujatie.vystupenie;

import com.example.dusky.myapplication.backend.model.database.podujatie.vystupenie.VystupenieRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.BaseEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

import java.util.Date;


/**
 * Created by dusky on 2/14/16.
 */
public class VystupenieResponseEntityModel extends BaseEntityModel {

    @ApiResourceProperty(name = "vystupenie_id")
    protected Long vystupenieId;

    @ApiResourceProperty(name = "podium_id")
    protected Long podiumId;

    @ApiResourceProperty(name = "detail")
    protected String detail;

    @ApiResourceProperty(name = "cas_od")
    protected Date casOd;

    @ApiResourceProperty(name = "cas_do")
    protected Date casDo;

    @ApiResourceProperty(name = "nazov")
    protected String nazov;

    public VystupenieResponseEntityModel(VystupenieRDBMSEntityModel podiumRDBMS) {
        super(podiumRDBMS);
        this.vystupenieId = podiumRDBMS.getVystupenieId();
        this.podiumId = podiumRDBMS.getPodiumId();
        this.detail = podiumRDBMS.getDetail();
        this.casOd = podiumRDBMS.getCasOd();
        this.casDo = podiumRDBMS.getCasDo();
        this.nazov = podiumRDBMS.getNazov();
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
        return "VystupenieResponseEntityModel{" +
                "vystupenieId=" + vystupenieId +
                ", podiumId=" + podiumId +
                ", detail='" + detail + '\'' +
                ", casOd=" + casOd +
                ", casDo=" + casDo +
                ", nazov='" + nazov + '\'' +
                '}';
    }
}
