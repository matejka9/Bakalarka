package festival.endpoint.podium;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import festival.endpoint.BaseEndpoint;
import festival.model.podium.PodiumResponseListEntityModel;

import java.util.Collection;

/**
 * Created by dusky on 2/14/16.
 */
@Api(
        name = "podium",
        canonicalName = "Global API Interface",
        version = "v1",
        description = "API for podium"
)
public class PodiumEndpoint extends BaseEndpoint{

    @ApiMethod(
            name = "mapa.count",
            path = "{id}/mapa/count",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Long countPodiaMapy(@Named("id") Long mapaId){

        return null;
    }

    @ApiMethod(
            name = "mapa",
            path = "{id}/mapa",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public PodiumResponseListEntityModel vsetkyPodiaMapy(@Named("id") Long mapaId,
                                                         @Named("batch") Long batch,
                                                         @Named("page") Long page){

        return null;
    }

    @ApiMethod(
            name = "vsetky.count",
            path = "vsetky/count",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Long countPodiaPodujatia(@Named("id") Long podujatieId){

        return null;
    }

    @ApiMethod(
            name = "vsetky",
            path = "vsetky",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public PodiumResponseListEntityModel vsetkyPodiaPodujatia(@Named("ids") Collection<Long> mapyIds,
                                                              @Named("batch") Long batch,
                                                              @Named("page") Long page){

        return null;
    }
}
