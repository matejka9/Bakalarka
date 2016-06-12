package com.example.dusky.myapplication.backend.manager;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by dusky on 5/18/16.
 */
public class DatabaseObjectMapper implements RowMapper {

    private static Logger logger = Logger.getLogger(DatabaseObjectMapper.class.getName());

    protected ResultSetMapper resultSetMapper;

    public DatabaseObjectMapper(Class mappingClass) {
        this.resultSetMapper = new ResultSetMapper(mappingClass);
    }

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        //logger.log(Level.INFO, "[" + i + "]: " + resultSet.toString() + ": " + resultSet.getMetaData());
        //ResultSetMetaData rsmd = resultSet.getMetaData();
        //int columnsNumber = rsmd.getColumnCount();
        //for (int index = 1; index <= columnsNumber; index++) {
        //    logger.log(Level.INFO, rsmd.getColumnName(index) + ": |" + resultSet.getObject(index) + "| <-> |" + resultSet.getString(index) + "|");
        //}
        return this.resultSetMapper.mapResultSetToObject(resultSet);
    }

}
