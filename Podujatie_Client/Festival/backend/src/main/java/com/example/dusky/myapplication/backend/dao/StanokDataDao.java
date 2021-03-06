package com.example.dusky.myapplication.backend.dao;

import com.example.dusky.myapplication.backend.manager.DatabaseObjectMapper;
import com.example.dusky.myapplication.backend.model.database.podujatie.stanok.StanokRDBMSEntityModel;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

/**
 * Created by dusky on 2/14/16.
 */
public class StanokDataDao extends BaseDataDao{
    public static List getStankyForPodujatie(NamedParameterJdbcTemplate jdbcTemplate, Long idMapa) {

        String sql = "SELECT " + "*" +
                        " FROM " + StanokRDBMSEntityModel.entityDescription.databaseAndTable() +
                        " WHERE " + StanokRDBMSEntityModel.entityDescription.columnMapaId + " = :mapaId" +
                        ";";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("mapaId", idMapa);

        return jdbcTemplate.query(sql, namedParameters, new DatabaseObjectMapper(StanokRDBMSEntityModel.class));
    }
}
