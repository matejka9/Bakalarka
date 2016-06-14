package com.dusky.festival.gae.connection.notifikacia;

import android.os.AsyncTask;

import com.dusky.festival.activity.detail.NotifikacieDetailListActivity;
import com.dusky.festival.adapter.notifikacia.NotifikaciaAdapter;
import com.example.dusky.myapplication.backend.notifikacia.Notifikacia;
import com.example.dusky.myapplication.backend.notifikacia.model.NotifikaciaResponseListEntityModel;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by dusky on 6/14/16.
 */
public class NotifikacieAsyncTask extends AsyncTask<Long, Void, NotifikaciaResponseListEntityModel> {
    private static Notifikacia myApiService = null;
    private final NotifikacieDetailListActivity notifikacieDetailListActivity;
    private final NotifikaciaAdapter adapter;

    public NotifikacieAsyncTask(NotifikacieDetailListActivity notifikacieDetailListActivity, NotifikaciaAdapter adapter) {
        this.notifikacieDetailListActivity = notifikacieDetailListActivity;
        this.adapter = adapter;
    }

    @Override
    protected NotifikaciaResponseListEntityModel doInBackground(Long... longs) {
        if(myApiService == null) {  // Only do this once


            Notifikacia.Builder builder = new Notifikacia.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://festival-1220.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        Long mapaId = longs[0];

        try {
            return myApiService.podujatie().notifikacie(mapaId).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa Stiahnut Notifikacie");
            return null;
        }
    }

    @Override
    protected void onPostExecute(NotifikaciaResponseListEntityModel result) {
        if (result != null && result.getNotifikacieList() != null) {
            adapter.succesDownload(result.getNotifikacieList());
            notifikacieDetailListActivity.setSuccesDownload();
        }else{
            adapter.failedDownload();
            notifikacieDetailListActivity.setErrorDownload();
        }
    }
}
