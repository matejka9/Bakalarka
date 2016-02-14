package festival.dao.rdbms;

import festival.model.podujatie.PodujatieResponseListEntityModel;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Date;

/**
 * Created by dusky on 2/14/16.
 */
public class PodujatieDataDao {

    public static Long countPodujatia(NamedParameterJdbcTemplate jdbcTemplate, Date from, Date to) {
        return null;
    }

    public static PodujatieResponseListEntityModel getPodujatia(NamedParameterJdbcTemplate jdbcTemplate, Date from, Date to, Long batch, Long page) {
        return null;
    }
}
