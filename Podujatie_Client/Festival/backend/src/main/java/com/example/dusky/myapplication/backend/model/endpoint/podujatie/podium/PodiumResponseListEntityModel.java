package com.example.dusky.myapplication.backend.model.endpoint.podujatie.podium;

import com.example.dusky.myapplication.backend.model.endpoint.BaseResponseListEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.stanok.StanokResponseEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

import java.util.List;

/**
 * Created by dusky on 2/14/16.
 */
public class PodiumResponseListEntityModel extends BaseResponseListEntityModel{

    @ApiResourceProperty(name = "podiaList")
    protected List<PodiumResponseEntityModel> podiaList;

    public PodiumResponseListEntityModel(List podiaDb) {
        this.podiaList = podiaDb;
    }

    public List<PodiumResponseEntityModel> getPodiaList() {
        return podiaList;
    }

    public void setPodiaList(List<PodiumResponseEntityModel> podiaList) {
        this.podiaList = podiaList;
    }

    @Override
    public String toString() {
        return "PodiumResponseListEntityModel{" +
                "podiaList=" + podiaList +
                '}';
    }
}
