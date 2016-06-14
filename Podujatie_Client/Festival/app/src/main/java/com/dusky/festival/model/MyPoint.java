package com.dusky.festival.model;

/**
 * Created by dusanmatejka on 6/14/16.
 */
public class MyPoint {
    private Long id;
    private double longitude;
    private double latitude;
    private String text;

    public MyPoint(double longitude, double latitude, String text) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.text = text;
    }

    public MyPoint() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
