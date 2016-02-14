package festival.endpoint.stanok;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import festival.endpoint.BaseEndpoint;
import festival.model.stanok.StanokResponseListEntityModel;

import java.util.Collection;

/**
 * Created by dusky on 2/14/16.
 */
@Api(
        name = "stanok",
        canonicalName = "Global API Interface",
        version = "v1",
        description = "API for stanok"
)
public class StanokEndpoint extends BaseEndpoint{

    @ApiMethod(
            name = "mapa.count",
            path = "{id}/mapa/count",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Long countStankyMapy(@Named("id") Long mapaId){

        return null;
    }

    @ApiMethod(
            name = "mapa",
            path = "{id}/mapa",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public StanokResponseListEntityModel stankyMapy(@Named("id") Long mapaId,
                                                      @Named("batch") Long batch,
                                                      @Named("page") Long page){

        return null;
    }

    @ApiMethod(
            name = "mapy.count",
            path = "mapy/count",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Long countStankyMap(@Named("ids") Collection<Long> mapyIds){

        return null;
    }

    @ApiMethod(
            name = "mapy",
            path = "mapy",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public StanokResponseListEntityModel stankyMap(@Named("ids") Collection<Long> mapyIds){

        return null;
    }
}
