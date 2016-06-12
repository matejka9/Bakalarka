package com.example.dusky.myapplication.backend.model.database.client;

import com.example.dusky.myapplication.backend.model.database.BaseRDBMSEntityDescription;

/**
 * Created by dusky on 5/18/16.
 */
public abstract class ClientBaseRDBMSEntityDescription extends BaseRDBMSEntityDescription {

    public static final String database = "client";

    @Override
    public String getDatabaseName() {
        return database;
    }
}
