package com.dusky.festival.gae.connection.podujatie;

import android.os.AsyncTask;
import android.util.Pair;

import com.dusky.festival.activity.base.menu.PodujatieListActivity;
import com.dusky.festival.adapter.podujatie.PodujatieAdapter;
import com.example.dusky.myapplication.backend.podujatie.Podujatie;
import com.example.dusky.myapplication.backend.podujatie.model.PodujatieResponseListEntityModel;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.util.DateTime;

import java.io.IOException;

/**
 * Created by dusky on 5/23/16.
 */
public class NajblizsiePodujatieAsyncTask extends AsyncTask<Pair<PodujatieAdapter, Pair<DateTime, Pair<Long, Long>>>, Void, PodujatieResponseListEntityModel> {
    private static Podujatie myApiService = null;
    private final PodujatieListActivity podujatieListActivity;
    private PodujatieAdapter adapter;

    public NajblizsiePodujatieAsyncTask(PodujatieListActivity podujatieListActivity) {
        this.podujatieListActivity = podujatieListActivity;
    }

    @Override
    protected PodujatieResponseListEntityModel doInBackground(Pair<PodujatieAdapter, Pair<DateTime, Pair<Long, Long>>>... params) {
        if(myApiService == null) {  // Only do this once


            Podujatie.Builder builder = new Podujatie.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://festival-1220.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        adapter = params[0].first;
        Pair<DateTime, Pair<Long, Long>> date_batchAndPage = params[0].second;
        DateTime from = date_batchAndPage.first;
        Pair<Long, Long> batchAndPage = date_batchAndPage.second;
        Long batch = batchAndPage.first;
        Long page = batchAndPage.second;

        try {
            return myApiService.vsetky(from).set("batch", batch).set("page", page).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa Stiahnut Podujatia");
            return null;
        }
    }

    @Override
    protected void onPostExecute(PodujatieResponseListEntityModel result) {
        if (result != null && result.getPodujatie() != null) {
            adapter.succesDownload(result.getPodujatie());
            podujatieListActivity.setSuccesDownload();
        }else{
            adapter.failedDownload();
            podujatieListActivity.setErrorDownload();
        }
    }
}
