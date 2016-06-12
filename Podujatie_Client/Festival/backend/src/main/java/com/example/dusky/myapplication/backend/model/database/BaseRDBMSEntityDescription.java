package com.example.dusky.myapplication.backend.model.database;

/**
 * Created by dusky on 5/18/16.
 */
public abstract class BaseRDBMSEntityDescription {

    public static final String columnId = "id";

    abstract public String getDatabaseName();
    abstract public String getTableName();

    //  Database Table
    public String databaseAndTable(){
        return getDatabaseName() + "." + getTableName();
    }

    //  Table
    public String columnWithTable(String column){
        return getTableName() + "." + column;
    }

    public String columnWithTableTemporary(String temporaryTable, String column){
        return temporaryTable + "." + column;
    }

    public String columnWithTableAsColumn(String column, String asColumn){
        return getTableName() + "." + column + " AS " + asColumn;
    }

    public String columnWithTableTemporaryAsColumn(String temporaryTable, String column, String asColumn){
        return temporaryTable + "." + column + " AS " + asColumn;
    }

    //  Database
    public String columnWithDatabase(String column) {
        return getDatabaseName() + "." + getTableName() + "." + column;
    }

    public String columnWithDatabaseAsColumn(String column, String asColumn) {
        return getDatabaseName() + "." + getTableName() + "." + column + " AS " + asColumn;
    }
}