package com.example.dusky.myapplication.backend.model.endpoint;

/**
 * Created by dusky on 5/16/16.
 */
public class CountEntityModel {

    protected Long count;

    public CountEntityModel(Long count){
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
