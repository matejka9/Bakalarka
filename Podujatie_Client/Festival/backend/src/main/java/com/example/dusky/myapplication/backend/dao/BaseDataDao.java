package com.example.dusky.myapplication.backend.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.Date;

/**
 * Created by dusky on 5/23/16.
 */
public class BaseDataDao {

    public static void addDateToWhere(Date from, Date to, MapSqlParameterSource namedParameters, StringBuilder build) {
        if (from != null && to != null) {
            build.append("BETWEEN :from AND :to ");
            namedParameters.addValue("from", from);
            namedParameters.addValue("to", to);
        } else {
            if (from != null) {
                build.append(">= :from ");
                namedParameters.addValue("from", from);
            }
            if (to != null) {
                build.append("<= :to ");
                namedParameters.addValue("to", to);
            }
        }
    }

    public static void addLimitAndOffset(Long offset, Long limit, MapSqlParameterSource namedParameters, StringBuilder build) {
        if (limit != null && limit > 0) {
            build.append(" LIMIT :limit");
            namedParameters.addValue("limit", limit);
            if (offset != null && offset > 0) {
                build.append(" OFFSET :offset");
                namedParameters.addValue("offset", offset);
            }
        }
    }
}
