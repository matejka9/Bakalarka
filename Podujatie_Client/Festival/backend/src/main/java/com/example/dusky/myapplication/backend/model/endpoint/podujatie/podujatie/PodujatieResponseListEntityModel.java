package com.example.dusky.myapplication.backend.model.endpoint.podujatie.podujatie;

import com.example.dusky.myapplication.backend.model.endpoint.BaseResponseListEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;

import java.util.List;

/**
 * Created by dusky on 2/14/16.
 */
public class PodujatieResponseListEntityModel extends BaseResponseListEntityModel {

    @ApiResourceProperty(name = "podujatie")
    protected List<PodujatieResponseEntityModel> podujatieList;

    public PodujatieResponseListEntityModel(Long batchCount, List<PodujatieResponseEntityModel> podujatieList) {
        super(batchCount);
        this.podujatieList = podujatieList;
    }

    public List<PodujatieResponseEntityModel> getPodujatieList() {
        return podujatieList;
    }

    public void setPodujatieList(List<PodujatieResponseEntityModel> podujatieList) {
        this.podujatieList = podujatieList;
    }

    @Override
    public String toString() {
        return "PodujatieResponseListEntityModel{" +
                "podujatieList=" + podujatieList +
                '}';
    }
}
