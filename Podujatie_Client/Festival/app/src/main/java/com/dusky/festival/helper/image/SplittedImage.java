package com.dusky.festival.helper.image;

import android.graphics.Bitmap;
import android.util.Pair;

import java.io.File;
import java.util.HashMap;

/**
 * Created by dusky on 5/24/16.
 */
public class SplittedImage {

    private HashMap<String, Bitmap> images;
    private Bitmap newFile;
    private int newWidth;
    private int newHeight;

    public SplittedImage(HashMap<String, Bitmap> images, Bitmap newFile, int newWidth, int newHeight) {
        this.images = images;
        this.newFile = newFile;
        this.newWidth = newWidth;
        this.newHeight = newHeight;
    }

    public HashMap<String, Bitmap> getImages() {
        return images;
    }

    public void setImages(HashMap<String, Bitmap> images) {
        this.images = images;
    }

    public Bitmap getNewFile() {
        return newFile;
    }

    public void setNewFile(Bitmap newFile) {
        this.newFile = newFile;
    }

    public int getNewWidth() {
        return newWidth;
    }

    public void setNewWidth(int newWidth) {
        this.newWidth = newWidth;
    }

    public int getNewHeight() {
        return newHeight;
    }

    public void setNewHeight(int newHeight) {
        this.newHeight = newHeight;
    }

    @Override
    public String toString() {
        return "SplittedImage{" +
                "images=" + images +
                ", newFile=" + newFile +
                ", newWidth=" + newWidth +
                ", newHeight=" + newHeight +
                '}';
    }
}
