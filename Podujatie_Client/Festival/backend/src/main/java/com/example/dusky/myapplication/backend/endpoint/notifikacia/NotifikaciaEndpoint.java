package com.example.dusky.myapplication.backend.endpoint.notifikacia;

import com.example.dusky.myapplication.backend.dao.NotifikaciaDataDao;
import com.example.dusky.myapplication.backend.endpoint.BaseEndpoint;
import com.example.dusky.myapplication.backend.manager.DatabaseManager;
import com.example.dusky.myapplication.backend.model.database.podujatie.notifikacia.NotifikaciaRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.notifikacia.NotifikaciaResponseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.notifikacia.NotifikaciaResponseListEntityModel;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.appengine.repackaged.com.google.common.base.Function;
import com.google.appengine.repackaged.com.google.common.collect.Lists;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by dusky on 6/14/16.
 */
@Api(
        name = "notifikacia",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.dusky.example.com",
                ownerName = "backend.myapplication.dusky.example.com",
                packagePath=""
        )
)
public class NotifikaciaEndpoint extends BaseEndpoint{
    protected static Logger logger = Logger.getLogger(NotifikaciaEndpoint.class.getName());

    @ApiMethod(
            name = "podujatie.notifikacie",
            path = "podujatie/notifikacie",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public NotifikaciaResponseListEntityModel notifikaciePodujatia(@Named("mapa_id") Long mapaId){
        NotifikaciaResponseListEntityModel notifikacieList = null;

        NamedParameterJdbcTemplate jdbcTemplate = DatabaseManager.getNamedTemplateData();

        List notifikacie = NotifikaciaDataDao.getNotifikacieOfPodujatie(jdbcTemplate, mapaId);
        if (notifikacie != null){
            notifikacie = Lists.transform(notifikacie, new Function<NotifikaciaRDBMSEntityModel, NotifikaciaResponseEntityModel>() {
                @Override
                public NotifikaciaResponseEntityModel apply(@Nullable NotifikaciaRDBMSEntityModel stanokRDBMS) {
                    return new NotifikaciaResponseEntityModel(stanokRDBMS);
                }
            });
        }
        notifikacieList = new NotifikaciaResponseListEntityModel(notifikacie);

        logger.log(Level.SEVERE, "" + notifikacieList);

        return notifikacieList;
    }
}
