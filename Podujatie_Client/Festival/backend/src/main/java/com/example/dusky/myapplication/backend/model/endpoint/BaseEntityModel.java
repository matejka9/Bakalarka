package com.example.dusky.myapplication.backend.model.endpoint;

import com.example.dusky.myapplication.backend.model.database.BaseRDBMSEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;

/**
 * Created by dusky on 5/18/16.
 */
public class BaseEntityModel implements Serializable{

    @ApiResourceProperty(name = "id")
    protected Long id;

    public BaseEntityModel(){

    }

    public BaseEntityModel(BaseRDBMSEntityModel base){
        this.id = base.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
