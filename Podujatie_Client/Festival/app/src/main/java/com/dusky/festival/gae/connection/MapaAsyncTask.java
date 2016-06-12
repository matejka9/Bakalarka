package com.dusky.festival.gae.connection;

import android.os.AsyncTask;
import android.util.Pair;

import com.dusky.festival.activity.base.map.MapTileViewActivity;
import com.dusky.festival.gae.connection.vystupenie.DownloadMapaAsyncTask;
import com.example.dusky.myapplication.backend.podujatie.Podujatie;
import com.example.dusky.myapplication.backend.podujatie.model.PodujatieDetailResponseEntityModel;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by dusky on 5/29/16.
 */
public class MapaAsyncTask extends AsyncTask<Pair<MapTileViewActivity, Long>, Void, PodujatieDetailResponseEntityModel> {
    private static Podujatie myApiService = null;
    private MapTileViewActivity activity;
    private Long idPodujatie;

    @Override
    protected PodujatieDetailResponseEntityModel doInBackground(Pair<MapTileViewActivity, Long>... podujatieAdapters) {
        if(myApiService == null) {  // Only do this once


            Podujatie.Builder builder = new Podujatie.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://festival-1220.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        this.activity = podujatieAdapters[0].first;
        this.idPodujatie = podujatieAdapters[0].second;

        try {
            return myApiService.detail(idPodujatie).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa Stiahnut Podujatia");
            return null;
        }
    }

    @Override
    protected void onPostExecute(PodujatieDetailResponseEntityModel result) {
        if (result != null && result.getMapa() != null && result.getMapa().getMapkaUrl() != null) {
            System.out.println("Obsahuje URL");
            activity.succesDownload(result);
            new DownloadMapaAsyncTask().execute(new Pair<MapTileViewActivity, String>(activity, result.getMapa().getMapkaUrl()));
        }else{
            activity.failedDownload();
        }
    }
}
