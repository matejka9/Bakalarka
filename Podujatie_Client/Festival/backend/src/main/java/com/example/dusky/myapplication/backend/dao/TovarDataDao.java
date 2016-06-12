package com.example.dusky.myapplication.backend.dao;

import com.example.dusky.myapplication.backend.manager.DatabaseObjectMapper;
import com.example.dusky.myapplication.backend.model.database.podujatie.tovar.TovarRDBMSEntityModel;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashSet;
import java.util.List;

/**
 * Created by dusky on 2/14/16.
 */
public class TovarDataDao extends BaseDataDao{
    public static List getTovarForStanky(NamedParameterJdbcTemplate jdbcTemplate, HashSet<Long> stankyIds) {
        if(stankyIds != null && stankyIds.size() > 0) {
            String sql = "SELECT " +
                    "T1." + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnId + " AS " + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnId +
                    ", T1." + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnStanokId + " AS " + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnStanokId +
                    ", T1." + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnTovarId + " AS " + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnTovarId +
                    ", T1." + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnDetail + " AS " + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnDetail +
                    ", T1." + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnCena + " AS " + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnCena +
                    ", T2." + TovarRDBMSEntityModel.entityDescriptionTovar.columnNazov + " AS " + TovarRDBMSEntityModel.entityDescriptionTovar.columnNazov +
                    " FROM " + TovarRDBMSEntityModel.entityDescriptionTovarStanku.databaseAndTable() + " AS T1" +
                    " LEFT JOIN " + TovarRDBMSEntityModel.entityDescriptionTovar.databaseAndTable() + " AS T2" +
                    " ON T1." + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnTovarId + " = T2." + TovarRDBMSEntityModel.entityDescriptionTovar.columnId +
                    " WHERE " + TovarRDBMSEntityModel.entityDescriptionTovarStanku.columnStanokId + " IN ( :stankyIds )" +
                    ";";
            MapSqlParameterSource namedParameters = new MapSqlParameterSource("stankyIds", stankyIds);

            return jdbcTemplate.query(sql, namedParameters, new DatabaseObjectMapper(TovarRDBMSEntityModel.class));
        }
        return null;
    }
}
