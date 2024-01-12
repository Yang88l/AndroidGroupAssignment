package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class book_history extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_book_history dbmanager_book_history;
    private com.example.groupassignment.dbhelpers.dbhelper_book_history dbhelper_book_history;

    private TextView location_text, cost_text, date_text, status_text;
    private String location, cost, date, status;
    public int user_id = 1; // Replace with the actual user ID


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_history);

        //Top Navigation
        BaseActivity.setupToolbar(this);

/*
        dbmanager_book_history = new dbmanager_book_history(this);
        dbhelper_book_history = new dbhelper_book_history(this);


        location_text = findViewById(R.id.state_location);
        cost_text = findViewById(R.id.cost);
        date_text = findViewById(R.id.date);
        status_text = findViewById(R.id.status);

        //Specify Specific Data you want to get
        dbmanager_book_history.open();

        String[] columnsToSelect = new String[]{
                dbhelper_book_history.LOCATION,
                dbhelper_book_history.DATE,
                dbhelper_book_history.COST,
                dbhelper_book_history.STATUS
        };

        //Retrieve the data
        Cursor cursor = dbmanager_book_history.fetch(user_id);

        // Get column indices
        int locationIndex = cursor.getColumnIndex(dbhelper_book_history.LOCATION);
        int dateIndex = cursor.getColumnIndex(dbhelper_book_history.DATE);
        int costIndex = cursor.getColumnIndex(dbhelper_book_history.COST);
        int statusIndex = cursor.getColumnIndex(dbhelper_book_history.STATUS);

        // Check if column indices are valid
        if (locationIndex >= 0 && dateIndex >= 0 && costIndex >= 0 && statusIndex >= 0) {
            // Extract values from the Cursor
            location = cursor.getString(locationIndex);
            date = cursor.getString(locationIndex);
            cost = cursor.getString(locationIndex);
            status = cursor.getString(locationIndex);
            ;
        }

        //Display the data
        location_text.setText(location);
        cost_text.setText(cost);
        date_text.setText(date);

        cursor.close();
        dbmanager_book_history.close();
        main.updateVersion();

        // if else to check if date is smaller than current date > display expire

 */
    }

    public void notification(View view) {
        startActivity(new Intent(this, notification.class));
    }
    public void home(View view) {
        startActivity(new Intent(this, main.class));
    }

    public void heart(View view) {
        startActivity(new Intent(this, my_favourite.class));
    }

    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }

    public void profile(View view) {
        startActivity(new Intent(this, profile.class));
    }

    public void plan_history(View view) {
        startActivity(new Intent(this, plan_history.class));
    }
}