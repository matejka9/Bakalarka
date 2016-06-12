package com.example.dusky.myapplication.backend.endpoint.podujatie;

import com.example.dusky.myapplication.backend.dao.MapaDataDao;
import com.example.dusky.myapplication.backend.dao.OstatneMiestaDataDao;
import com.example.dusky.myapplication.backend.dao.PodiumDataDao;
import com.example.dusky.myapplication.backend.dao.StanokDataDao;
import com.example.dusky.myapplication.backend.dao.TovarDataDao;
import com.example.dusky.myapplication.backend.dao.VystupenieDataDao;
import com.example.dusky.myapplication.backend.model.database.podujatie.mapa.MapaRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.database.podujatie.ostatneMiesta.OstatneMiestaRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.database.podujatie.podium.PodiumRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.database.podujatie.podujatie.PodujatieRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.database.podujatie.stanok.StanokRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.database.podujatie.tovar.TovarRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.database.podujatie.vystupenie.VystupenieRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.CountEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.mapa.MapaResponseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.ostatneMiesta.OstatneMiestaResponseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.ostatneMiesta.OstatneMiestaResponseListEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.podium.PodiumResponseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.podium.PodiumResponseListEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.podujatie.PodujatieDetailResponseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.podujatie.PodujatieResponseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.stanok.StanokResponseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.stanok.StanokResponseListEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.tovar.TovarResponseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.tovar.TovarResponseListEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.vystupenie.VystupenieResponseEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.vystupenie.VystupenieResponseListEntityModel;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.example.dusky.myapplication.backend.dao.PodujatieDataDao;
import com.example.dusky.myapplication.backend.endpoint.BaseEndpoint;
import com.example.dusky.myapplication.backend.manager.DatabaseManager;
import com.example.dusky.myapplication.backend.model.endpoint.podujatie.podujatie.PodujatieResponseListEntityModel;
import com.google.appengine.repackaged.com.google.common.base.Function;
import com.google.appengine.repackaged.com.google.common.collect.Lists;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dusky on 2/14/16.
 */
@Api(
        name = "podujatie",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.dusky.example.com",
                ownerName = "backend.myapplication.dusky.example.com",
                packagePath=""
        )
)
public class PodujatieEndpoint extends BaseEndpoint{

    protected static Logger logger = Logger.getLogger(PodujatieEndpoint.class.getName());

    @ApiMethod(
            name = "vsetkyCount",
            path = "vsetkyCount",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public CountEntityModel countPodujatia(@Named("from") Date from, @Named("userId") Date userId){
        CountEntityModel count = null;

        NamedParameterJdbcTemplate jdbcTemplate = DatabaseManager.getNamedTemplateData();

        Long pocet = PodujatieDataDao.countPodujatia(jdbcTemplate, from, null);

        count = new CountEntityModel(pocet);

        return count;
    }

    @ApiMethod(
            name = "vsetky",
            path = "vsetky",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public PodujatieResponseListEntityModel vsetkyPodujatia(@Named("from") Date from,
                                                            @Nullable @Named("batch") Long batch,
                                                            @Nullable @Named("page") Long page){
        PodujatieResponseListEntityModel response = null;
        NamedParameterJdbcTemplate jdbcTemplate = DatabaseManager.getNamedTemplateData();

        logger.log(Level.SEVERE, "Vyberam Data");

        List podujatia = PodujatieDataDao.getPodujatia(jdbcTemplate, from, null, page * batch, batch);
        logger.log(Level.SEVERE, "Vybrate Data");
        if (podujatia != null) {
            logger.log(Level.SEVERE, podujatia.toString());
            podujatia = Lists.transform(podujatia, new Function<PodujatieRDBMSEntityModel, PodujatieResponseEntityModel>() {
                @Override
                public PodujatieResponseEntityModel apply(@Nullable PodujatieRDBMSEntityModel podujatieRDBMS) {
                    return new PodujatieResponseEntityModel(podujatieRDBMS);
                }
            });

            response = new PodujatieResponseListEntityModel(batch, podujatia);
            logger.log(Level.SEVERE, response.toString());
        }

        return response;
    }

    @ApiMethod(
            name = "detail",
            path = "detail",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public PodujatieDetailResponseEntityModel getPodujatie(@Named("idPodujatia") Long idPodujatia){
        PodujatieDetailResponseEntityModel response = new PodujatieDetailResponseEntityModel(idPodujatia);
        NamedParameterJdbcTemplate jdbcTemplate = DatabaseManager.getNamedTemplateData();

        //Mapa
        MapaRDBMSEntityModel mapaDb = MapaDataDao.getMapaOfPodujatie(jdbcTemplate, idPodujatia);
        MapaResponseEntityModel mapa = new MapaResponseEntityModel(mapaDb);
        response.setMapa(mapa);

        //Stanky
        List stankyDb = StanokDataDao.getStankyForPodujatie(jdbcTemplate, mapa.getId());
        if (stankyDb != null){
            stankyDb = Lists.transform(stankyDb, new Function<StanokRDBMSEntityModel, StanokResponseEntityModel>() {
                @Override
                public StanokResponseEntityModel apply(@Nullable StanokRDBMSEntityModel stanokRDBMS) {
                    return new StanokResponseEntityModel(stanokRDBMS);
                }
            });
        }
        StanokResponseListEntityModel stanky = new StanokResponseListEntityModel(stankyDb);
        response.setStanky(stanky);

        //Tovar
        HashSet<Long> stankyIds = new HashSet<Long>();
        for (Object stanok :stankyDb){
            stankyIds.add(((StanokResponseEntityModel) stanok).getId());
        }

        List tovarDb = TovarDataDao.getTovarForStanky(jdbcTemplate, stankyIds);
        if (tovarDb != null){
            tovarDb = Lists.transform(tovarDb, new Function<TovarRDBMSEntityModel, TovarResponseEntityModel>() {
                @Override
                public TovarResponseEntityModel apply(@Nullable TovarRDBMSEntityModel tovarRDBMS) {
                    return new TovarResponseEntityModel(tovarRDBMS);
                }
            });
        }
        TovarResponseListEntityModel tovar = new TovarResponseListEntityModel(tovarDb);
        response.setTovar(tovar);

        //Podia
        List podiaDb = PodiumDataDao.getPodiaForMapa(jdbcTemplate, mapa.getId());
        if (podiaDb != null){
            podiaDb = Lists.transform(podiaDb, new Function<PodiumRDBMSEntityModel, PodiumResponseEntityModel>() {
                @Override
                public PodiumResponseEntityModel apply(@Nullable PodiumRDBMSEntityModel podiumRDBMS) {
                    return new PodiumResponseEntityModel(podiumRDBMS);
                }
            });
        }
        PodiumResponseListEntityModel podia = new PodiumResponseListEntityModel(podiaDb);
        response.setPodia(podia);

        //Vystupenia
        HashSet<Long> podiaIds = new HashSet<Long>();
        for (Object podium :podiaDb){
            podiaIds.add(((PodiumResponseEntityModel) podium).getId());
        }

        List vystupeniaDb = VystupenieDataDao.getVystupeniaForPodia(jdbcTemplate, podiaIds);
        if (vystupeniaDb != null){
            vystupeniaDb = Lists.transform(vystupeniaDb, new Function<VystupenieRDBMSEntityModel, VystupenieResponseEntityModel>() {
                @Override
                public VystupenieResponseEntityModel apply(@Nullable VystupenieRDBMSEntityModel vystupenieRDBMS) {
                    return new VystupenieResponseEntityModel(vystupenieRDBMS);
                }
            });
        }
        VystupenieResponseListEntityModel vystupenia = new VystupenieResponseListEntityModel(vystupeniaDb);
        response.setVystupenia(vystupenia);

        //Ostatne budovy
        List ostatneMiestaDb = OstatneMiestaDataDao.getOstatneMiestaForMapa(jdbcTemplate, mapa.getId());
        if (ostatneMiestaDb != null){
            ostatneMiestaDb = Lists.transform(ostatneMiestaDb, new Function<OstatneMiestaRDBMSEntityModel, OstatneMiestaResponseEntityModel>() {
                @Override
                public OstatneMiestaResponseEntityModel apply(@Nullable OstatneMiestaRDBMSEntityModel ostatneMiestoRDBMS) {
                    return new OstatneMiestaResponseEntityModel(ostatneMiestoRDBMS);
                }
            });
        }
        OstatneMiestaResponseListEntityModel ostatneMiesta = new OstatneMiestaResponseListEntityModel(ostatneMiestaDb);
        response.setOstatneMiesta(ostatneMiesta);

        logger.log(Level.SEVERE, "Response " + response);

        return response;
    }


}
