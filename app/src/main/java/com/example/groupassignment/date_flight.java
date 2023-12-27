package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class date_flight extends AppCompatActivity {
    private Button button;
    private Button button2;
    private dbmanager_flight dbmanager_flight;
    private dbhelper_flight dbhelper_flight;
    private String date;
    public int user_id=1; // Replace with the actual user ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_flight);

        button = findViewById(R.id.next);
        button2 = findViewById(R.id.date);

        dbmanager_flight = new dbmanager_flight(this);
        dbhelper_flight = new dbhelper_flight(this);


        //Specify Specific Data you want to get
        dbmanager_flight.open();

        String[] columnsToSelect = new String[]{
                dbhelper_flight.DATE,
        };

        //Retrieve the data
        Cursor cursor = dbmanager_flight.fetch(user_id);

        // Get column indices
        int dateIndex = cursor.getColumnIndex(dbhelper_flight.DATE);

        // Check if column indices are valid
        if (dateIndex >= 0) {
            // Extract values from the Cursor
            date = cursor.getString(dateIndex);
        }

        cursor.close();
        dbmanager_flight.close();





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(date_flight.this, pax_flight.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(date_flight.this, calendar_flight.class);
                startActivity(intent);
            }
        });
    }
}