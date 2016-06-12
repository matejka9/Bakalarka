package com.example.dusky.myapplication.backend.dao;

import com.example.dusky.myapplication.backend.manager.DatabaseObjectMapper;
import com.example.dusky.myapplication.backend.model.database.podujatie.ostatneMiesta.OstatneMiestaRDBMSEntityModel;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

/**
 * Created by dusky on 5/29/16.
 */
public class OstatneMiestaDataDao extends BaseDataDao{
    public static List getOstatneMiestaForMapa(NamedParameterJdbcTemplate jdbcTemplate, Long idMapa) {
        String sql = "SELECT " + "*" +
                " FROM " + OstatneMiestaRDBMSEntityModel.entityDescription.databaseAndTable() +
                " WHERE " + OstatneMiestaRDBMSEntityModel.entityDescription.columnMapaId + " = :mapaId" +
                ";";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("mapaId", idMapa);

        return jdbcTemplate.query(sql, namedParameters, new DatabaseObjectMapper(OstatneMiestaRDBMSEntityModel.class));
    }
}
