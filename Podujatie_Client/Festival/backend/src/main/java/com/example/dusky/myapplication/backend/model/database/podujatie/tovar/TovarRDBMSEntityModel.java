package com.example.dusky.myapplication.backend.model.database.podujatie.tovar;

import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityDescription;
import com.example.dusky.myapplication.backend.model.database.podujatie.PodujatieBaseRDBMSEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dusky on 5/29/16.
 */
@Entity
@Table(name = TovarRDBMSEntityModel.TovarStankuRDBMSEntityDescription.table)
public class TovarRDBMSEntityModel extends PodujatieBaseRDBMSEntityModel {

    public static class TovarStankuRDBMSEntityDescription extends PodujatieBaseRDBMSEntityDescription {

        public static final String table = "tovar_stanku_tovarstanku";

        public static final String columnStanokId = "stanok_id";
        public static final String columnTovarId = "tovar_id";
        public static final String columnDetail = "detail";
        public static final String columnCena = "cena";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static TovarStankuRDBMSEntityDescription entityDescriptionTovarStanku = new TovarStankuRDBMSEntityDescription();

    public static class TovarRDBMSEntityDescription extends PodujatieBaseRDBMSEntityDescription {

        public static final String table = "tovar_tovar";

        public static final String columnNazov = "nazov";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static TovarRDBMSEntityDescription entityDescriptionTovar = new TovarRDBMSEntityDescription();

    @Column(name = TovarStankuRDBMSEntityDescription.columnStanokId)
    protected Long stanokId;

    @Column(name = TovarStankuRDBMSEntityDescription.columnTovarId)
    protected Long tovarId;

    @Column(name = TovarStankuRDBMSEntityDescription.columnDetail)
    protected String detail;

    @Column(name = TovarStankuRDBMSEntityDescription.columnCena)
    protected Double cena;

    @Column(name = TovarRDBMSEntityDescription.columnNazov)
    protected String nazov;

    public static TovarStankuRDBMSEntityDescription getEntityDescriptionTovarStanku() {
        return entityDescriptionTovarStanku;
    }

    public static void setEntityDescriptionTovarStanku(TovarStankuRDBMSEntityDescription entityDescriptionTovarStanku) {
        TovarRDBMSEntityModel.entityDescriptionTovarStanku = entityDescriptionTovarStanku;
    }

    public static TovarRDBMSEntityDescription getEntityDescriptionTovar() {
        return entityDescriptionTovar;
    }

    public static void setEntityDescriptionTovar(TovarRDBMSEntityDescription entityDescriptionTovar) {
        TovarRDBMSEntityModel.entityDescriptionTovar = entityDescriptionTovar;
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
        return "TovarRDBMSEntityModel{" +
                "stanokId=" + stanokId +
                ", tovarId=" + tovarId +
                ", detail='" + detail + '\'' +
                ", cena=" + cena +
                ", nazov='" + nazov + '\'' +
                '}';
    }
}
