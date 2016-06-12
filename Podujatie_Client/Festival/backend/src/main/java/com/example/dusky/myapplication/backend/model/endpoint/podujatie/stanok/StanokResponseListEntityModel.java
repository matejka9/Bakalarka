package com.example.dusky.myapplication.backend.model.endpoint.podujatie.stanok;

import com.example.dusky.myapplication.backend.model.endpoint.BaseResponseListEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

import java.util.List;

/**
 * Created by dusky on 2/14/16.
 */
public class StanokResponseListEntityModel extends BaseResponseListEntityModel{

    @ApiResourceProperty(name = "stankyList")
    protected List<StanokResponseEntityModel> stankyList;

    public StanokResponseListEntityModel(List stankyDb) {
        this.stankyList = stankyDb;
    }

    public List<StanokResponseEntityModel> getStankyList() {
        return stankyList;
    }

    public void setStankyList(List<StanokResponseEntityModel> stankyList) {
        this.stankyList = stankyList;
    }

    @Override
    public String toString() {
        return "StanokResponseListEntityModel{" +
                "stankyList=" + stankyList +
                '}';
    }
}
