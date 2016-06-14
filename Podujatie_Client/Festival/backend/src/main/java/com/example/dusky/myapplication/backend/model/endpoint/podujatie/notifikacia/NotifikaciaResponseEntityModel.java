package com.example.dusky.myapplication.backend.model.endpoint.podujatie.notifikacia;

import com.example.dusky.myapplication.backend.model.database.podujatie.notifikacia.NotifikaciaRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.BaseEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

/**
 * Created by dusky on 6/14/16.
 */
public class NotifikaciaResponseEntityModel extends BaseEntityModel {

    @ApiResourceProperty(name = "mapa_id")
    protected Long mapaId;

    @ApiResourceProperty(name = "nadpis")
    protected String nadpis;

    @ApiResourceProperty(name = "text")
    protected String text;

    @ApiResourceProperty(name = "detail")
    protected String detail;

    public NotifikaciaResponseEntityModel(NotifikaciaRDBMSEntityModel notifikaciaRDBMS) {
        super(notifikaciaRDBMS);
        this.mapaId = notifikaciaRDBMS.getMapaId();
        this.nadpis = notifikaciaRDBMS.getNadpis();
        this.text = notifikaciaRDBMS.getText();
        this.detail = notifikaciaRDBMS.getDetail();
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
        return "NotifikaciaResponseEntityModel{" +
                "mapaId=" + mapaId +
                ", nadpis='" + nadpis + '\'' +
                ", text='" + text + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
