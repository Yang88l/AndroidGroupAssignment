package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class calendar_flight extends AppCompatActivity {
    private Button button;
    private dbmanager_flight dbmanager_flight;
    private CalendarView calendarView;
    private String date;
    private int user_id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_flight);

        button = findViewById(R.id.confirm);
        calendarView = findViewById(R.id.calendarView);


        dbmanager_flight = new dbmanager_flight(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //date = calendarView.getText().toString();

                dbmanager_flight.open();
                dbmanager_flight.insert(date, "","","",user_id);
                dbmanager_flight.close();

                Intent intent = new Intent(calendar_flight.this, date_flight.class);
                startActivity(intent);
            }
        });
    }
}