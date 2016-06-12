package com.example.dusky.myapplication.backend.endpoint.user;

import com.example.dusky.myapplication.backend.dao.UserDataDao;
import com.example.dusky.myapplication.backend.manager.DatabaseManager;
import com.example.dusky.myapplication.backend.model.database.client.user.UserRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.client.user.UserEntityModel;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.example.dusky.myapplication.backend.endpoint.BaseEndpoint;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by dusky on 5/18/16.
 */
@Api(
        name = "user",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.dusky.example.com",
                ownerName = "backend.myapplication.dusky.example.com",
                packagePath=""
        )
)
public class UserEndpoint extends BaseEndpoint{

    @ApiMethod(
            name = "register",
            path = "register",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public UserEntityModel register(UserEntityModel inputModel) throws Exception {
        System.out.println(inputModel);
        UserEntityModel result = null;

        NamedParameterJdbcTemplate jdbcTemplate = DatabaseManager.getNamedTemplateData();

        UserRDBMSEntityModel user = UserDataDao.getUserByMail(jdbcTemplate, inputModel);
        if (user == null || (user.getId() == null)){
            UserDataDao.createUser(jdbcTemplate, inputModel);

            user = UserDataDao.getUserByMail(jdbcTemplate, inputModel);
            if (user != null) {
                result = new UserEntityModel(user);
            }else{
                throw new Exception("Error during creating User");
            }
        }else{
            throw new Exception("Mail already is in use.");
        }
        return result;
    }

    @ApiMethod(
            name = "logIn",
            path = "logIn",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public UserEntityModel logIn(UserEntityModel inputModel) throws Exception {
        UserEntityModel result = null;
        NamedParameterJdbcTemplate jdbcTemplate = DatabaseManager.getNamedTemplateData();

        UserRDBMSEntityModel user = UserDataDao.logIn(jdbcTemplate, inputModel);
        if (user != null && user.getId() != null){
            result = new UserEntityModel(user);
        }else{
            throw new Exception("Wrong email or password.");
        }

        return result;
    }


}