package com.example.dusky.myapplication.backend.dao;

import com.example.dusky.myapplication.backend.manager.DatabaseObjectMapper;
import com.example.dusky.myapplication.backend.model.database.podujatie.notifikacia.NotifikaciaRDBMSEntityModel;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

/**
 * Created by dusky on 6/14/16.
 */
public class NotifikaciaDataDao extends BaseDataDao {

    public static List getNotifikacieOfPodujatie(NamedParameterJdbcTemplate jdbcTemplate, Long idMapa) {
        String sql = "SELECT " + "*" +
                " FROM " + NotifikaciaRDBMSEntityModel.entityDescription.databaseAndTable() +
                " WHERE " + NotifikaciaRDBMSEntityModel.entityDescription.columnMapaId + " = :mapaId" +
                ";";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("mapaId", idMapa);

        return jdbcTemplate.query(sql, namedParameters, new DatabaseObjectMapper(NotifikaciaRDBMSEntityModel.class));
    }

}
