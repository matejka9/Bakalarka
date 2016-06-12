package com.dusky.festival.gae.connection.vystupenie;

import android.os.AsyncTask;
import android.util.Pair;

import com.dusky.festival.activity.base.menu.PodujatieListActivity;
import com.dusky.festival.adapter.podujatie.PodujatieAdapter;
import com.dusky.festival.database.MojeVystupeniaDBHelper;
import com.example.dusky.myapplication.backend.podujatie.Podujatie;
import com.example.dusky.myapplication.backend.podujatie.model.PodujatieResponseEntityModel;
import com.example.dusky.myapplication.backend.podujatie.model.PodujatieResponseListEntityModel;
import com.example.dusky.myapplication.backend.podujatie.model.VystupenieResponseEntityModel;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.util.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dusky on 5/28/16.
 */
public class MojeVystupenieAsyncTask extends AsyncTask<Pair<PodujatieAdapter, Pair<DateTime, Pair<Long, Long>>>, Void, Pair<PodujatieResponseListEntityModel, HashSet<Long>>> {
    private static Podujatie myApiService = null;
    private MojeVystupeniaDBHelper db;
    private final PodujatieListActivity podujatieListActivity;
    private PodujatieAdapter adapter;

    public MojeVystupenieAsyncTask(PodujatieListActivity podujatieListActivity) {
        this.podujatieListActivity = podujatieListActivity;
        db = new MojeVystupeniaDBHelper(podujatieListActivity);
    }

    @Override
    protected Pair<PodujatieResponseListEntityModel, HashSet<Long>> doInBackground(Pair<PodujatieAdapter, Pair<DateTime, Pair<Long, Long>>>... params) {
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
            PodujatieResponseListEntityModel result1 = myApiService.vsetky(from).set("batch", batch).set("page", page).execute();
            HashSet<Long> result2 = db.getPodiaIds();
            return new Pair<PodujatieResponseListEntityModel, HashSet<Long>>(result1, result2);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa Stiahnut Podujatia");
            return null;
        }
    }

    @Override
    protected void onPostExecute(Pair<PodujatieResponseListEntityModel, HashSet<Long>> result) {
        if (result != null && result.first != null &&result.first.getPodujatie() != null) {
            List<PodujatieResponseEntityModel> podujatia = new ArrayList<PodujatieResponseEntityModel>();
            for (PodujatieResponseEntityModel podujatie: result.first.getPodujatie()){
                if (result.second.contains(podujatie.getId())){
                    podujatia.add(podujatie);
                }
            }
            adapter.succesDownload(podujatia);
            podujatieListActivity.setSuccesDownload();
        }else{
            adapter.failedDownload();
            podujatieListActivity.setErrorDownload();
        }
    }
}
