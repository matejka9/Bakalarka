package com.example.dusky.myapplication.backend.manager;

import com.google.appengine.api.utils.SystemProperty;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by dusky on 2/14/16.
 */
public class DatabaseManager {
    protected static Logger logger = Logger.getLogger(DatabaseManager.class.getName());

    protected static DataSource dataSourceData = null;


    public static DataSource getDataSourceData() {
        if (dataSourceData == null) {
            try {
                Properties info = new Properties();
                String url = null;

                if (SystemProperty.environment.value() ==
                        SystemProperty.Environment.Value.Production) {
                    url = System.getProperty("cloudsql.url.client");
                    // Load the class that provides the new "jdbc:google:mysql://" prefix.
                    Class.forName("com.mysql.jdbc.GoogleDriver");
                    logger.log(Level.SEVERE, "Google");
                } else {
                    // Local MySQL instance to use during development.
                    Class.forName("com.mysql.jdbc.Driver");

                    info.put("user", "root");
                    info.put("password", "root");

                    url = System.getProperty("cloudsql.url.client.dev");
                    logger.log(Level.SEVERE, "Mysql");
                    //url = "jdbc:mysql://127.0.0.1:3306/client";
                }

                info.put("characterEncoding", "utf8");

                dataSourceData = new DriverManagerDataSource(url, info);
            }
            catch (Exception e) {
                logger.log(Level.FINEST, e.toString());
            }
        }
        return dataSourceData;
    }

    public static NamedParameterJdbcTemplate getNamedTemplateData() {
        DataSource dataSource = getDataSourceData();
        if (dataSource != null) {
            return new NamedParameterJdbcTemplate(dataSource);
        }
        return null;
    }
}
