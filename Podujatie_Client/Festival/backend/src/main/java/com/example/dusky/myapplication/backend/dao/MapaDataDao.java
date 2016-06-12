package com.example.dusky.myapplication.backend.dao;

import com.example.dusky.myapplication.backend.manager.DatabaseObjectMapper;
import com.example.dusky.myapplication.backend.model.database.client.user.UserRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.database.podujatie.mapa.MapaRDBMSEntityModel;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by dusky on 5/29/16.
 */
public class MapaDataDao extends BaseDataDao{

    public static MapaRDBMSEntityModel getMapaOfPodujatie(NamedParameterJdbcTemplate jdbcTemplate, Long idPodujatie) {
        if (idPodujatie != null){
            try {
                String sql =
                        "SELECT " + "*" +
                                " FROM " + MapaRDBMSEntityModel.entityDescription.databaseAndTable() +
                                " WHERE " + MapaRDBMSEntityModel.entityDescription.columnPodujatieId + " = :podujatieId" +
                                ";";
                MapSqlParameterSource namedParameters = new MapSqlParameterSource("podujatieId", idPodujatie);
                System.out.println(jdbcTemplate);
                return (MapaRDBMSEntityModel) jdbcTemplate.queryForObject(sql, namedParameters, new DatabaseObjectMapper(MapaRDBMSEntityModel.class));
            }catch (EmptyResultDataAccessException e) {
                return null;
            }
        }
        return null;
    }
}
