package com.example.dusky.myapplication.backend.dao;

import com.example.dusky.myapplication.backend.manager.DatabaseObjectMapper;
import com.example.dusky.myapplication.backend.model.database.podujatie.vystupenie.VystupenieRDBMSEntityModel;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashSet;
import java.util.List;

/**
 * Created by dusky on 2/14/16.
 */
public class VystupenieDataDao extends BaseDataDao{
    public static List getVystupeniaForPodia(NamedParameterJdbcTemplate jdbcTemplate, HashSet<Long> podiaIds) {
        if(podiaIds != null && podiaIds.size() > 0) {
            String sql = "SELECT " +
                    "T1." + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnId + " AS " + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnId +
                    ", T1." + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnVystupenieId + " AS " + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnVystupenieId +
                    ", T1." + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnPodiumId + " AS " + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnPodiumId +
                    ", T1." + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnDetail + " AS " + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnDetail +
                    ", T1." + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnCasOd + " AS " + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnCasOd +
                    ", T1." + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnCasDo + " AS " + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnCasDo +
                    ", T2." + VystupenieRDBMSEntityModel.entityDescriptionVystupenie.columnNazov + " AS " + VystupenieRDBMSEntityModel.entityDescriptionVystupenie.columnNazov +
                    " FROM " + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.databaseAndTable() + " AS T1" +
                    " LEFT JOIN " + VystupenieRDBMSEntityModel.entityDescriptionVystupenie.databaseAndTable() + " AS T2" +
                    " ON T1." + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnVystupenieId + " = T2." + VystupenieRDBMSEntityModel.entityDescriptionVystupenie.columnId +
                    " WHERE " + VystupenieRDBMSEntityModel.entityDescriptionVystupeniePodia.columnPodiumId + " IN ( :podiaIds )" +
                    ";";
            MapSqlParameterSource namedParameters = new MapSqlParameterSource("podiaIds", podiaIds);

            return jdbcTemplate.query(sql, namedParameters, new DatabaseObjectMapper(VystupenieRDBMSEntityModel.class));
        }
        return null;
    }
}
