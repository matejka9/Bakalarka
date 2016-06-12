package com.dusky.festival.gae.connection.podujatie;

import android.os.AsyncTask;

import com.dusky.festival.activity.base.menu.PodujatieListActivity;
import com.dusky.festival.adapter.podujatie.PodujatieAdapter;
import com.dusky.festival.database.MojePodujatiaDBHelper;
import com.example.dusky.myapplication.backend.podujatie.model.PodujatieResponseEntityModel;

import java.util.ArrayList;

/**
 * Created by dusky on 5/29/16.
 */
public class MojePodujatiaAsyncTask extends AsyncTask<PodujatieAdapter, Void, ArrayList<PodujatieResponseEntityModel>> {
    private MojePodujatiaDBHelper db;
    private final PodujatieListActivity podujatieListActivity;
    private PodujatieAdapter adapter;

    public MojePodujatiaAsyncTask(PodujatieListActivity podujatieListActivity) {
        this.podujatieListActivity = podujatieListActivity;
        db = new MojePodujatiaDBHelper(podujatieListActivity);
    }


    @Override
    protected ArrayList<PodujatieResponseEntityModel> doInBackground(PodujatieAdapter... podujatieAdapters) {
        this.adapter = podujatieAdapters[0];

        return db.getAllPodujatie();
    }

    @Override
    protected void onPostExecute(ArrayList<PodujatieResponseEntityModel> result) {
        if (result != null) {
            adapter.succesDownload(result);
            podujatieListActivity.setSuccesDownload();
        }else{
            adapter.failedDownload();
            podujatieListActivity.setErrorDownload();
        }
    }
}
