package com.example.dusky.myapplication.backend.model.endpoint.podujatie.tovar;

import com.example.dusky.myapplication.backend.model.database.podujatie.tovar.TovarRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.BaseEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

/**
 * Created by dusky on 2/14/16.
 */
public class TovarResponseEntityModel extends BaseEntityModel {

    @ApiResourceProperty(name = "stanok_id")
    protected Long stanokId;

    @ApiResourceProperty(name = "tovar_id")
    protected Long tovarId;

    @ApiResourceProperty(name = "detail")
    protected String detail;

    @ApiResourceProperty(name = "cena")
    protected Double cena;

    @ApiResourceProperty(name = "nazov")
    protected String nazov;

    public TovarResponseEntityModel(TovarRDBMSEntityModel tovarRDBMS) {
        super(tovarRDBMS);
        this.stanokId = tovarRDBMS.getStanokId();
        this.tovarId = tovarRDBMS.getTovarId();
        this.detail = tovarRDBMS.getDetail();
        this.cena = tovarRDBMS.getCena();
        this.nazov = tovarRDBMS.getNazov();
    }

    public Long getStanokId() {
        return stanokId;
    }

    public void setStanokId(Long stanokId) {
        this.stanokId = stanokId;
    }

    public Long getTovarId() {
        return tovarId;
    }

    public void setTovarId(Long tovarId) {
        this.tovarId = tovarId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String toString() {
        return "TovarResponseEntityModel{" +
                "stanokId=" + stanokId +
                ", tovarId=" + tovarId +
                ", detail='" + detail + '\'' +
                ", cena=" + cena +
                ", nazov='" + nazov + '\'' +
                '}';
    }
}
