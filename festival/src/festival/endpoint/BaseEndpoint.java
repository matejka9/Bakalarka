package festival.endpoint;

import festival.model.user.UserDSEntityModel;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by dusky on 2/14/16.
 */
public class BaseEndpoint {

    protected Long getUserId() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null &&
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDSEntityModel){
            return ((UserDSEntityModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        }
        return null;
    }
}
