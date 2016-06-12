package com.example.dusky.myapplication.backend.model.database.podujatie;

import com.example.dusky.myapplication.backend.model.database.BaseRDBMSEntityDescription;

/**
 * Created by dusky on 5/23/16.
 */
public abstract class PodujatieBaseRDBMSEntityDescription extends BaseRDBMSEntityDescription {

    public static final String database = "podujatie";

    @Override
    public String getDatabaseName() {
        return database;
    }

}
