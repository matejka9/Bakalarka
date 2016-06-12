package com.dusky.festival.helper.image;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Pair;

import java.util.HashMap;


/**
 * Created by dusky on 5/24/16.
 */
public class SplitCutterImage {

    public static SplittedImage splitImage(Bitmap originalImage, Long id){
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        int fitTileWidth = 256;
        int fitTileHeight = 256;

        int numberOfColumns = (width/fitTileWidth) + (width%fitTileWidth == 0 ? 0 : 1);
        int numberOfRows = (height/fitTileHeight) + (height%fitTileHeight == 0 ? 0 : 1);

        int newWidth = numberOfColumns * fitTileWidth;
        int newHeight = numberOfRows * fitTileWidth;

        Bitmap newImage = Bitmap.createScaledBitmap(originalImage, newWidth, newHeight, false);
        System.out.println(newHeight);

        HashMap<String, Bitmap> splitImages = new HashMap<String, Bitmap>();

        for (int y = 0; y < numberOfRows; y++) {
            for (int x = 0; x < numberOfColumns; x++) {

                // calculate the scale - in this case = 0.4f
                float scaleWidth = ((float) fitTileWidth) / newWidth;
                float scaleHeight = ((float) fitTileHeight) / newHeight;

                // createa matrix for the manipulation
                Matrix matrix = new Matrix();
                // resize the bit map
                //matrix.postScale(scaleWidth, scaleHeight);

                Bitmap bm = Bitmap.createBitmap(newImage, x*fitTileWidth, y*fitTileHeight, fitTileWidth, fitTileHeight, matrix, true);
                splitImages.put(getKey(id, x, y), bm);
            }
        }

        SplittedImage splitedImage = new SplittedImage(splitImages, newImage, newWidth, newHeight);

        return splitedImage;
    }

    public static String getKey(Long id, int column , int row){
        return id + "Mapa-" + column + "-" + row + ".jpeg";
    }

    public static String getKeyForMap(Long id){
        return id + "Mapa-%d-%d.jpeg";
    }


}
