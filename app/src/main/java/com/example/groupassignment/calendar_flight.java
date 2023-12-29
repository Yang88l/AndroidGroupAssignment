package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

public class calendar_flight extends AppCompatActivity {
    private Button button;
    private dbmanager_flight dbmanager_flight;
    private CalendarView calendarView;
    private String date;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_flight);

        button = findViewById(R.id.confirm);

        dbmanager_flight = new dbmanager_flight(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //date = calendarView.getText().toString();
                //date="27/12/2023";
                EditText date = findViewById(R.id.date);
                user_id=1;

                dbmanager_flight.open();
                dbmanager_flight.update(null,null,null, String.valueOf(date),null,null);
                dbmanager_flight.close();
                Intent intent = new Intent(calendar_flight.this, time_flight.class);
                startActivity(intent);
            }
        });
    }
}