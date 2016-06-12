package com.example.dusky.myapplication.backend.dao;

import com.example.dusky.myapplication.backend.manager.DatabaseObjectMapper;
import com.example.dusky.myapplication.backend.model.database.client.user.UserRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.client.user.UserEntityModel;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by dusky on 5/18/16.
 */
public class UserDataDao extends BaseDataDao{

    public static void createUser(NamedParameterJdbcTemplate jdbcTemplate, UserEntityModel user) throws Exception {
        if (user != null && user.getPassword() != null && user.getEmail() != null) {

            String insert = "INSERT INTO " + UserRDBMSEntityModel.entityDescription.databaseAndTable() +
                    " (" +
                    UserRDBMSEntityModel.entityDescription.columnEmail + ", " +
                    UserRDBMSEntityModel.entityDescription.columnPassword +
                    ") VALUES (" +
                    "'" + user.getEmail() + "', " +
                    "'" + user.getPassword() + "'" +
                    ");";

            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            jdbcTemplate.update(insert, namedParameters);
        }else {
            throw new Exception("Bad input parameters");
        }
    }

    public static UserRDBMSEntityModel getUserByMail(NamedParameterJdbcTemplate jdbcTemplate, UserEntityModel user) throws Exception {
        if (user != null & user.getEmail() != null){
            try {
                String sql =
                        "SELECT " + UserRDBMSEntityModel.entityDescription.columnId + ", " +
                                UserRDBMSEntityModel.entityDescription.columnEmail + ", " +
                                UserRDBMSEntityModel.entityDescription.columnPassword +
                                " FROM " + UserRDBMSEntityModel.entityDescription.databaseAndTable() +
                                " WHERE " + UserRDBMSEntityModel.entityDescription.columnEmail + " = :userEmail" +
                                ";";
                MapSqlParameterSource namedParameters = new MapSqlParameterSource("userEmail", user.getEmail());
                System.out.println(jdbcTemplate);
                return (UserRDBMSEntityModel) jdbcTemplate.queryForObject(sql, namedParameters, new DatabaseObjectMapper(UserRDBMSEntityModel.class));
            }catch (EmptyResultDataAccessException e) {
                return null;
            }
        }
        throw new Exception("Bad input parameters");
    }

    public static UserRDBMSEntityModel logIn(NamedParameterJdbcTemplate jdbcTemplate, UserEntityModel user) throws Exception {
        if (user != null & user.getEmail() != null && user.getPassword() != null){
            try {
                String sql =
                        "SELECT " + UserRDBMSEntityModel.entityDescription.columnId + ", " +
                                UserRDBMSEntityModel.entityDescription.columnEmail + ", " +
                                UserRDBMSEntityModel.entityDescription.columnPassword +
                                " FROM " + UserRDBMSEntityModel.entityDescription.databaseAndTable() +
                                " WHERE " + UserRDBMSEntityModel.entityDescription.columnEmail + " = :userEmail" +
                                " AND " + UserRDBMSEntityModel.entityDescription.columnPassword + " = :password" +
                                ";";
                MapSqlParameterSource namedParameters = new MapSqlParameterSource("userEmail", user.getEmail());
                namedParameters.addValue("password", user.getPassword());
                return (UserRDBMSEntityModel) jdbcTemplate.queryForObject(sql, namedParameters, new DatabaseObjectMapper(UserRDBMSEntityModel.class));
            }catch (EmptyResultDataAccessException e) {
                return null;
            }
        }
        throw new Exception("Bad input parameters");
    }
}
