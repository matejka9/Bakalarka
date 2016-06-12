package com.dusky.festival.activity.base.map.provider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.qozix.tileview.graphics.BitmapProvider;
import com.qozix.tileview.tiles.Tile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dusky on 5/29/16.
 */
public class BitmapProviderLocalFiles implements BitmapProvider{

    private final String path;

    public BitmapProviderLocalFiles(String path){
        this.path = path;
    }
    public Bitmap getBitmap(Tile tile, Context context ) {
        Object data = tile.getData();
        if( data instanceof String ) {

            try {
                FileInputStream in =
                        context.openFileInput(
                                String.format(
                                        path, tile.getColumn(),
                                        tile.getRow() ));
                return BitmapFactory.decodeStream(in);
            } catch (FileNotFoundException e) {
            }
            return null;
        }
        return null;
    }
}
