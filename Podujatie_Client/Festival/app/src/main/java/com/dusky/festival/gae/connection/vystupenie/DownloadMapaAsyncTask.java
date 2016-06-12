package com.dusky.festival.gae.connection.vystupenie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Pair;

import com.dusky.festival.activity.base.map.MapTileViewActivity;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dusky on 5/29/16.
 */
public class DownloadMapaAsyncTask extends AsyncTask<Pair<MapTileViewActivity, String>, Void, Bitmap> {
    private MapTileViewActivity activity;
    private Long idPodujatie;
    private String urlRequested;

    @Override
    protected Bitmap doInBackground(Pair<MapTileViewActivity, String>... podujatieAdapters) {
        this.activity = podujatieAdapters[0].first;
        this.urlRequested = podujatieAdapters[0].second;

        try {
            URL url = new URL(urlRequested);
            URLConnection conn = url.openConnection();
            return BitmapFactory.decodeStream(conn.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            activity.succesDownloadMap(result);
        }else{
            activity.failedDownload();
        }
    }
}
