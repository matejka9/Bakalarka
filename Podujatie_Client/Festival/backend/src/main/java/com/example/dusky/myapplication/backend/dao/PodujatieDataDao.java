package com.example.dusky.myapplication.backend.dao;

import com.example.dusky.myapplication.backend.manager.DatabaseObjectMapper;
import com.example.dusky.myapplication.backend.model.database.podujatie.podujatie.PodujatieRDBMSEntityModel;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dusky on 2/14/16.
 */
public class PodujatieDataDao extends BaseDataDao{

    protected static Logger logger = Logger.getLogger(PodujatieDataDao.class.getName());

    public static Long countPodujatia(NamedParameterJdbcTemplate jdbcTemplate, Date from, Date to) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        StringBuilder build = new StringBuilder("SELECT COUNT(1)" +
                                                " FROM " + PodujatieRDBMSEntityModel.entityDescription.databaseAndTable());

        if (from != null || to != null){
            build.append(" WHERE " + PodujatieRDBMSEntityModel.entityDescription.columnDatumOd);
            addDateToWhere(from, to, namedParameters, build);
            build.append(" OR "+ PodujatieRDBMSEntityModel.entityDescription.columnDatumDo);
            addDateToWhere(from, to, namedParameters, build);
        }

        build.append(";");
        return jdbcTemplate.queryForObject(build.toString(), namedParameters, Long.class);
    }

    public static List getPodujatia(NamedParameterJdbcTemplate jdbcTemplate, Date from, Date to, Long offset, Long limit) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        StringBuilder build = new StringBuilder("SELECT " +
                PodujatieRDBMSEntityModel.entityDescription.columnId + ", " +
                PodujatieRDBMSEntityModel.entityDescription.columnNazov + ", " +
                PodujatieRDBMSEntityModel.entityDescription.columnTyp + ", " +
                PodujatieRDBMSEntityModel.entityDescription.columnDatumOd + ", " +
                PodujatieRDBMSEntityModel.entityDescription.columnDatumDo +
                " FROM " + PodujatieRDBMSEntityModel.entityDescription.databaseAndTable());

        if (from != null || to != null){
            build.append(" WHERE " + PodujatieRDBMSEntityModel.entityDescription.columnDatumOd);
            addDateToWhere(from, to, namedParameters, build);
            build.append(" OR "+ PodujatieRDBMSEntityModel.entityDescription.columnDatumDo);
            addDateToWhere(from, to, namedParameters, build);
        }

        build.append(" ORDER BY " +
                    PodujatieRDBMSEntityModel.entityDescription.columnDatumOd  + " ");

        if (offset != null && limit != null){
            addLimitAndOffset(offset, limit, namedParameters, build);
        }

        logger.log(Level.SEVERE, build.toString());
        logger.log(Level.SEVERE, namedParameters.getValues().toString());

        return jdbcTemplate.query(build.toString(), namedParameters, new DatabaseObjectMapper(PodujatieRDBMSEntityModel.class));
    }
}
