package com.dusky.festival.gae.connection.user;

import android.os.AsyncTask;
import android.util.Pair;

import com.dusky.festival.activity.base.login.RegisterActivity;
import com.example.dusky.myapplication.backend.user.model.UserEntityModel;
import com.example.dusky.myapplication.backend.user.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by dusky on 5/18/16.
 */
public class RegisterUserAsyncTask extends AsyncTask<Pair<RegisterActivity, UserEntityModel>, Void, UserEntityModel> {
    private static User myApiService = null;
    private RegisterActivity context;

    @Override
    protected UserEntityModel doInBackground(Pair<RegisterActivity, UserEntityModel>... params) {
        if(myApiService == null) {  // Only do this once


            User.Builder builder = new User.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://festival-1220.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        context = params[0].first;
        UserEntityModel creatingUser = params[0].second;

        try {
            return myApiService.register().set("email", creatingUser.getEmail()).set("password", creatingUser.getPassword()).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa registrovat");
            return null;
        }
    }

    @Override
    protected void onPostExecute(UserEntityModel result) {
        if (result != null && result.getId() != null) {
            context.succesRegister(result);
        }else{
            context.failedRegister();
        }
    }
}
