package com.example.dusky.myapplication.backend.model.endpoint.client.user;

import com.example.dusky.myapplication.backend.model.database.client.user.UserRDBMSEntityModel;
import com.example.dusky.myapplication.backend.model.endpoint.BaseEntityModel;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.annotation.Entity;

/**
 * Created by dusky on 2/14/16.
 */
//@Entity
public class UserEntityModel extends BaseEntityModel {

    @ApiResourceProperty(name = "email")
    protected String email;

    @ApiResourceProperty(name = "password")
    protected String password;

    public UserEntityModel(){
        super();
    }

    public UserEntityModel(UserRDBMSEntityModel model){
        super(model);
        this.email = model.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntityModel{" +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
