package festival.endpoint.tovar;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import festival.endpoint.BaseEndpoint;
import festival.model.tovar.TovarResponseListEntityModel;

import java.util.Collection;

/**
 * Created by dusky on 2/14/16.
 */
@Api(
        name = "tovar",
        canonicalName = "Global API Interface",
        version = "v1",
        description = "API for tovar"
)
public class TovarEndpoint extends BaseEndpoint{

    @ApiMethod(
            name = "stanok.count",
            path = "{id}/stanok/count",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Long countTovarStanku(@Named("id") Long stanokId){

        return null;
    }

    @ApiMethod(
            name = "stanok",
            path = "{id}/stanok",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public TovarResponseListEntityModel tovarStanku(@Named("id") Long stanokId,
                                                   @Named("batch") Long batch,
                                                   @Named("page") Long page){

        return null;
    }

    @ApiMethod(
            name = "stanky.count",
            path = "stanky/count",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Long countTovarStankov(@Named("ids") Collection<Long> stankyIds){

        return null;
    }

    @ApiMethod(
            name = "stanky",
            path = "stanky",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public TovarResponseListEntityModel tovarStankov(@Named("ids") Collection<Long> stankyIds){

        return null;
    }
}
