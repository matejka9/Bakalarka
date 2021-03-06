package com.dusky.festival.gae.connection.user;

import android.os.AsyncTask;
import android.util.Pair;

import com.dusky.festival.activity.base.login.LoginActivity;
import com.example.dusky.myapplication.backend.user.User;
import com.example.dusky.myapplication.backend.user.model.UserEntityModel;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by dusky on 5/22/16.
 */
public class LoginUserAsyncTask extends AsyncTask<Pair<LoginActivity, UserEntityModel>, Void, UserEntityModel> {
    private static User myApiService = null;
    private LoginActivity context;

    @Override
    protected UserEntityModel doInBackground(Pair<LoginActivity, UserEntityModel>... params) {
        if(myApiService == null) {  // Only do this once


            User.Builder builder = new User.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://festival-1220.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        context = params[0].first;
        UserEntityModel logInUser = params[0].second;

        try {
            return myApiService.logIn().set("email", logInUser.getEmail()).set("password", logInUser.getPassword()).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa prihlasit");
            return null;
        }
    }

    @Override
    protected void onPostExecute(UserEntityModel result) {
        if (result != null && result.getId() != null) {
            context.succesLogin(result);
        }else{
            context.failedLogin();
        }
    }
}