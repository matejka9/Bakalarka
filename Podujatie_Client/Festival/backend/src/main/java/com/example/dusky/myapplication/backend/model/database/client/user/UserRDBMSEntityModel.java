package com.example.dusky.myapplication.backend.model.database.client.user;


import com.example.dusky.myapplication.backend.model.database.client.ClientBaseRDBMSEntityDescription;
import com.example.dusky.myapplication.backend.model.database.client.ClientBaseRDBMSEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dusky on 5/18/16.
 */
@Entity
@Table(name = UserRDBMSEntityModel.UserRDBMSEntityDescription.table)
public class UserRDBMSEntityModel extends ClientBaseRDBMSEntityModel{

    public static class UserRDBMSEntityDescription extends ClientBaseRDBMSEntityDescription {

        public static final String table = "user";

        public static final String columnEmail = "email";
        public static final String columnPassword = "password";

        @Override
        public String getTableName() {
            return table;
        }
    }

    public static UserRDBMSEntityDescription entityDescription = new UserRDBMSEntityDescription();

    @Column(name = UserRDBMSEntityDescription.columnEmail)
    protected String email;

    @Column(name = UserRDBMSEntityDescription.columnPassword)
    protected String password;

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
        return "UserRDBMSEntityModel{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
