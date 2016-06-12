package com.example.dusky.myapplication.backend.model.endpoint.podujatie.tovar;

import com.example.dusky.myapplication.backend.model.endpoint.BaseResponseListEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

import java.util.List;

/**
 * Created by dusky on 2/14/16.
 */
public class TovarResponseListEntityModel extends BaseResponseListEntityModel{

    @ApiResourceProperty(name = "tovarList")
    protected List<TovarResponseEntityModel> tovarList;

    public TovarResponseListEntityModel(List tovarDb) {
        this.tovarList = tovarDb;
    }

    public List<TovarResponseEntityModel> getTovarList() {
        return tovarList;
    }

    public void setTovarList(List<TovarResponseEntityModel> tovarList) {
        this.tovarList = tovarList;
    }

    @Override
    public String toString() {
        return "TovarResponseListEntityModel{" +
                "tovarList=" + tovarList +
                '}';
    }
}
