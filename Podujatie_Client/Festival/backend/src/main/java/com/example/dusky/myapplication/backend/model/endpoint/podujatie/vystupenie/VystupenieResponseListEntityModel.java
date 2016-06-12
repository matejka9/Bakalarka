package com.example.dusky.myapplication.backend.model.endpoint.podujatie.vystupenie;

import com.example.dusky.myapplication.backend.model.endpoint.BaseResponseListEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

import java.util.List;

/**
 * Created by dusky on 2/14/16.
 */
public class VystupenieResponseListEntityModel extends BaseResponseListEntityModel {

    @ApiResourceProperty(name = "vystupeniaList")
    protected List<VystupenieResponseEntityModel> vystupeniaList;

    public VystupenieResponseListEntityModel(List vystupeniaDb) {
        this.vystupeniaList = vystupeniaDb;
    }

    public List<VystupenieResponseEntityModel> getVystupeniaList() {
        return vystupeniaList;
    }

    public void setVystupeniaList(List<VystupenieResponseEntityModel> vystupeniaList) {
        this.vystupeniaList = vystupeniaList;
    }

    @Override
    public String toString() {
        return "VystupenieResponseListEntityModel{" +
                "vystupeniaList=" + vystupeniaList +
                '}';
    }
}
