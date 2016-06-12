package com.example.dusky.myapplication.backend.model.database;

import javax.persistence.Column;

/**
 * Created by dusky on 5/18/16.
 */
public abstract class BaseRDBMSEntityModel extends BaseDataEntityModel{

    @Column(name = BaseRDBMSEntityDescription.columnId)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
