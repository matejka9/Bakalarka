package festival.endpoint.skupina;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import festival.endpoint.BaseEndpoint;
import festival.model.skupina.SkupinaResponseListEntityModel;

import java.util.Collection;

/**
 * Created by dusky on 2/14/16.
 */
@Api(
        name = "skupina",
        canonicalName = "Global API Interface",
        version = "v1",
        description = "API for skupina"
)
public class SkupinaEndpoint extends BaseEndpoint{

    @ApiMethod(
            name = "podia.count",
            path = "{id}/podia/count",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Long countSkupinyPodia(@Named("id") Long podiumId){

        return null;
    }

    @ApiMethod(
            name = "podia",
            path = "{id}/podia",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public SkupinaResponseListEntityModel skupinyPodia(@Named("id") Long podiumId,
                                                       @Named("batch") Long batch,
                                                       @Named("page") Long page){

        return null;
    }

    @ApiMethod(
            name = "podii.count",
            path = "podii/count",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Long countSkupinyPodii(@Named("ids") Collection<Long> podiaIds){

        return null;
    }

    @ApiMethod(
            name = "podii",
            path = "podii",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public SkupinaResponseListEntityModel skupinyPodii(@Named("ids") Collection<Long> podiaIds){

        return null;
    }
}
