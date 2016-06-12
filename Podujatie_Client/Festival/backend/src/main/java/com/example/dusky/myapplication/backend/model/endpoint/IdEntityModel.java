package com.example.dusky.myapplication.backend.model.endpoint;

import com.google.api.server.spi.config.ApiResourceProperty;

/**
 * Created by dusky on 5/18/16.
 */
public class IdEntityModel {

    @ApiResourceProperty(name = "id")
    protected Long id;

    public IdEntityModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
