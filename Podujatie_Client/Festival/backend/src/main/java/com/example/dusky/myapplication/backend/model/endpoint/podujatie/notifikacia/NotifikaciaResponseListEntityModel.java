package com.example.dusky.myapplication.backend.model.endpoint.podujatie.notifikacia;

import com.example.dusky.myapplication.backend.model.endpoint.BaseResponseListEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

import java.util.List;

/**
 * Created by dusky on 6/14/16.
 */
public class NotifikaciaResponseListEntityModel extends BaseResponseListEntityModel {

    @ApiResourceProperty(name = "notifikacieList")
    protected List<NotifikaciaResponseEntityModel> notifikacieList;

    public NotifikaciaResponseListEntityModel(List notifikacieList) {
        this.notifikacieList = notifikacieList;
    }

    public List<NotifikaciaResponseEntityModel> getNotifikacieList() {
        return notifikacieList;
    }

    public void setNotifikacieList(List<NotifikaciaResponseEntityModel> notifikacieList) {
        this.notifikacieList = notifikacieList;
    }

    @Override
    public String toString() {
        return "NotifikaciaResponseListEntityModel{" +
                "notifikacieList=" + notifikacieList +
                '}';
    }
}
