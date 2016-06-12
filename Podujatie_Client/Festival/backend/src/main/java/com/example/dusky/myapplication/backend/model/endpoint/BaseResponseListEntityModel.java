package com.example.dusky.myapplication.backend.model.endpoint;

import com.google.api.server.spi.config.ApiResourceProperty;

import java.io.Serializable;

/**
 * Created by dusky on 5/23/16.
 */
public class BaseResponseListEntityModel implements Serializable{
    @ApiResourceProperty(name = "count_batch")
    protected Long batchCount;

    public BaseResponseListEntityModel(Long batchCount) {
        this.batchCount = batchCount;
    }

    public BaseResponseListEntityModel() {
    }

    public Long getBatchCount() {
        return batchCount;
    }

    public void setBatchCount(Long batchCount) {
        this.batchCount = batchCount;
    }
}
