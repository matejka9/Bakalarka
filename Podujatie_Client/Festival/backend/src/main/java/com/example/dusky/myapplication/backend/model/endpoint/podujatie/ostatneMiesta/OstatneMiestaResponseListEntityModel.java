package com.example.dusky.myapplication.backend.model.endpoint.podujatie.ostatneMiesta;

import com.example.dusky.myapplication.backend.model.endpoint.BaseResponseListEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

import java.util.List;

/**
 * Created by dusky on 5/29/16.
 */
public class OstatneMiestaResponseListEntityModel extends BaseResponseListEntityModel {

    @ApiResourceProperty(name = "ostatneMiestaList")
    protected List<OstatneMiestaResponseEntityModel> ostatneMiestaList;

    public OstatneMiestaResponseListEntityModel(List ostatneMiestaDb) {
        this.ostatneMiestaList = ostatneMiestaDb;
    }

    public List<OstatneMiestaResponseEntityModel> getOstatneMiestaList() {
        return ostatneMiestaList;
    }

    public void setOstatneMiestaList(List<OstatneMiestaResponseEntityModel> ostatneMiestaList) {
        this.ostatneMiestaList = ostatneMiestaList;
    }

    @Override
    public String toString() {
        return "PodiumResponseListEntityModel{" +
                "ostatneMiestaList=" + ostatneMiestaList +
                '}';
    }
}
