package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class info_flight extends AppCompatActivity {
    private dbmanager_flight dbmanager_flight;
    private  dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;
    private com.example.groupassignment.dbmanager_book_summary dbmanager_book_summary;
    private com.example.groupassignment.dbmanager_plan_summary dbmanager_plan_summary;
    private com.example.groupassignment.dbmanager_choose_airline dbmanager_choose_airline;

    /*
        private Button button;
        private dbmanager_flight dbmanager_flight;
        private dbhelper_flight dbhelper_flight;
        private TextView flight_information_text;

        private String flight_number, departure_time;
        public int user_id=1; // Replace with the actual user ID
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_flight);

        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_user = new dbmanager_user(this);

        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch("0");
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        dbmanager_user.close();

        dbmanager_flight.open();
        Cursor cursor1 = dbmanager_flight.fetch(user_id);
        String date = cursor1.getString(3);
        cursor.moveToLast();
        dbmanager_flight.close();

        TextView text = findViewById(R.id.flight_information_text);
        text.setText("You have made a flight booking on "+date);


/*
        button = findViewById(R.id.finish2);


        dbmanager_flight = new dbmanager_flight(this);
        dbhelper_flight = new dbhelper_flight(this);

        flight_information_text = findViewById(R.id.flight_information_text);

        //Specify Specific Data you want to get
        dbmanager_flight.open();

        String[] columnsToSelect = new String[]{
                dbhelper_flight.FLIGHT_NUMBER,
                dbhelper_flight.DEPARTURE_TIME,
        };

        //Retrieve the data
        Cursor cursor = dbmanager_flight.fetch(user_id);

        // Get column indices
        int flight_numberIndex = cursor.getColumnIndex(dbhelper_flight.FLIGHT_NUMBER);
        int departure_timeIndex = cursor.getColumnIndex(dbhelper_flight.DEPARTURE_TIME);

        // Check if column indices are valid
        if (flight_numberIndex >= 0 && departure_timeIndex >= 0) {
            // Extract values from the Cursor
            flight_number = cursor.getString(flight_numberIndex);
            departure_time = cursor.getString(departure_timeIndex);
        }

        //Display the data
        flight_information_text.setText("Your Flight Number is: " + flight_number + "\n" +
                                        "Your Departure Time is: " + departure_time);

        cursor.close();
        dbmanager_flight.close();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(info_flight.this, transport.class);
                startActivity(intent);
            }
        });
        */
    }

    public void finish(View view) {
        //get activity
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        String activity = cursor_login.getString(2);

        //get user id
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(1));
        dbmanager_login_history.close();

        //get airline id
        dbmanager_choose_airline = new dbmanager_choose_airline(this);
        dbmanager_choose_airline.open();
        Cursor cursor_airline = dbmanager_choose_airline.fetch();
        cursor_airline.moveToLast();
        int airline_id=Integer.parseInt(cursor_airline.getString(1));
        dbmanager_choose_airline.close();

        if (activity=="plan") {
            dbmanager_plan_summary = new dbmanager_plan_summary(this);
            dbmanager_plan_summary.open();
            dbmanager_plan_summary.insert("flight", airline_id, user_id);
            dbmanager_plan_summary.close();
            Intent intent = new Intent(this,planning_summary.class);
            startActivity(intent);
        }
        else if (activity=="book") {
            dbmanager_book_summary = new dbmanager_book_summary(this);
            dbmanager_book_summary.open();
            dbmanager_book_summary.insert("flight", airline_id, user_id);
            dbmanager_book_summary.close();
            Intent intent = new Intent(this,booking_summary.class);
            startActivity(intent);
        }
    }
}