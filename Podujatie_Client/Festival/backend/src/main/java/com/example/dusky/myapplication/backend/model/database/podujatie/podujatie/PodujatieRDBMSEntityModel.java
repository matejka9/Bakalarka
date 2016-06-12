package com.example.dusky.myapplication.backend.model.database.podujatie.podujatie;

import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityDescription;
import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dusky on 5/23/16.
 */
@Entity
@Table(name = PodujatieRDBMSEntityModel.PodujatieRDBMSEntityDescription.table)
public class PodujatieRDBMSEntityModel extends PodujatieBaseRDBMSEntityModel {

    public static class PodujatieRDBMSEntityDescription extends PodujatieBaseRDBMSEntityDescription {

        public static final String table = "Podujatie_podujatie";

        public static final String columnNazov = "nazov";
        public static final String columnTyp = "typ";
        public static final String columnDatumOd = "datum_od";
        public static final String columnDatumDo = "datum_do";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static PodujatieRDBMSEntityDescription entityDescription = new PodujatieRDBMSEntityDescription();

    @Column(name = PodujatieRDBMSEntityDescription.columnNazov)
    protected String nazov;

    @Column(name = PodujatieRDBMSEntityDescription.columnTyp)
    protected String typ;

    @Column(name = PodujatieRDBMSEntityDescription.columnDatumOd)
    protected Date datumOd;

    @Column(name = PodujatieRDBMSEntityDescription.columnDatumDo)
    protected Date datumDo;

    public static PodujatieRDBMSEntityDescription getEntityDescription() {
        return entityDescription;
    }

    public static void setEntityDescription(PodujatieRDBMSEntityDescription entityDescription) {
        PodujatieRDBMSEntityModel.entityDescription = entityDescription;
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
        return "PodujatieRDBMSEntityModel{" +
                "nazov='" + nazov + '\'' +
                ", typ='" + typ + '\'' +
                ", datumOd=" + datumOd +
                ", datumDo=" + datumDo +
                '}';
    }
}
