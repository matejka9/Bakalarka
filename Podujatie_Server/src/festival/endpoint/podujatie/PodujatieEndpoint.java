package festival.endpoint.podujatie;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import festival.dao.rdbms.PodujatieDataDao;
import festival.endpoint.BaseEndpoint;
import festival.manager.data.rdbms.DatabaseManager;
import festival.model.podujatie.PodujatieResponseListEntityModel;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Date;

/**
 * Created by dusky on 2/14/16.
 */
@Api(
        name = "podujatie",
        canonicalName = "Global API Interface",
        version = "v1",
        description = "API for podujatie"
)
public class PodujatieEndpoint extends BaseEndpoint{

    @ApiMethod(
            name = "vsetky.count",
            path = "vsetky/count",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Long countPodujatia(@Named("from") Date from){

        NamedParameterJdbcTemplate jdbcTemplate = DatabaseManager.getNamedTamplateData();

        Long pocet = PodujatieDataDao.countPodujatia(jdbcTemplate, from, null);

        return pocet;
    }

    @ApiMethod(
            name = "vsetky",
            path = "vsetky",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public PodujatieResponseListEntityModel vsetkyPodujatia(@Named("from") Date from,
                                                            @Nullable @Named("batch") Long batch,
                                                            @Nullable @Named("page") Long page){
        NamedParameterJdbcTemplate jdbcTemplate = DatabaseManager.getNamedTamplateData();

        PodujatieResponseListEntityModel podujatia = PodujatieDataDao.getPodujatia(jdbcTemplate, from, null, batch, page);

        return podujatia;
    }


}
