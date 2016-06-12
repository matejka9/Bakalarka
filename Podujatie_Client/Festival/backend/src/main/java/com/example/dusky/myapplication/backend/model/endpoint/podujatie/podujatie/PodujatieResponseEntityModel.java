package com.example.dusky.myapplication.backend.model.endpoint.podujatie.podujatie;

import com.example.dusky.myapplication.backend.model.database.podujatie.podujatie.PodujatieRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.BaseEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

import java.util.Date;

/**
 * Created by dusky on 2/14/16.
 */
public class PodujatieResponseEntityModel extends BaseEntityModel {

    @ApiResourceProperty(name = "nazov")
    protected String nazov;

    @ApiResourceProperty(name = "typ")
    protected String typ;

    @ApiResourceProperty(name = "datum_od")
    protected Date datumOd;

    @ApiResourceProperty(name = "datum_do")
    protected Date datumDo;

    public PodujatieResponseEntityModel(){
        super();
    }

    public PodujatieResponseEntityModel(PodujatieRDBMSEntityModel model){
        super(model);
        this.nazov = model.getNazov();
        this.typ = model.getTyp();
        this.datumOd = model.getDatumOd();
        this.datumDo = model.getDatumDo();
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    @Override
    public String toString() {
        return "PodujatieResponseEntityModel{" +
                "id=" + id +
                ", nazov='" + nazov + '\'' +
                ", typ='" + typ + '\'' +
                ", datumOd=" + datumOd +
                ", datumDo=" + datumDo +
                '}';
    }
}
