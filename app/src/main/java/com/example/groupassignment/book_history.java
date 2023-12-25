package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class book_history extends AppCompatActivity {
    private dbmanager_book_history dbmanager_book_history;
    private dbhelper_book_history dbhelper_book_history;
    private TextView locations, costs, dates, statuses ;
    public int book_history_id;
    public String location, cost, date, status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_history);

        dbmanager_book_history = new dbmanager_book_history(this);
        dbhelper_book_history =  new dbhelper_book_history(this);

        locations = findViewById(R.id.state_location);
        costs = findViewById(R.id.cost);
        dates = findViewById(R.id.date);
        statuses = findViewById(R.id.status);

        //Get DATA
        ArrayList<book_history> data = dbhelper_book_history.fetch_book_history();

        //Display DATA
        //locations.setText();
    }

    public void home(View view) {
        Intent intent = new Intent (this, main.class);
        startActivity(intent);
    }

    public void heart(View view) {
        Intent intent = new Intent (this, my_favourite.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent (this, book_history.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void planhistory(View view) {
        Intent intent = new Intent (this, plan_history.class);
        startActivity(intent);
    }
}