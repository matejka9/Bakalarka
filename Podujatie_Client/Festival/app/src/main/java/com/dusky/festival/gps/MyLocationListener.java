package com.dusky.festival.gps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;

import com.dusky.festival.activity.base.map.MapTileViewActivity;

/**
 * Created by dusky on 5/30/16.
 */
public class MyLocationListener implements LocationListener {

    private final MapTileViewActivity mapa;

    public MyLocationListener(MapTileViewActivity mapa){
        this.mapa = mapa;
    }

    @Override
    public void onLocationChanged(Location loc) {
        mapa.changePosition(loc.getLatitude(), loc.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
