package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class filter extends AppCompatActivity {
    private dbmanager_filter_range dbmanager_filter_range;
    private String availability;
    private double price,distance;
    private int rating, filter_id=1, user_id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);

        dbmanager_filter_range = new dbmanager_filter_range(this);

    }

    public void minPrice(View view) {
    }

    public void maxPrice(View view) {
    }

    public void minRate(View view) {
    }

    public void maxRate(View view) {
    }

    public void minAvailable(View view) {
    }

    public void maxAvailable(View view) {
    }

    public void minDistance(View view) {
    }

    public void maxDistance(View view) {
    }

    public void cancel(View view) {
    }

    public void reset(View view) {
    }

    public void save(View view) {
        dbmanager_filter_range.open();
        dbmanager_filter_range.insert(price, distance,rating,availability,user_id);
        dbmanager_filter_range.close();
    }
}