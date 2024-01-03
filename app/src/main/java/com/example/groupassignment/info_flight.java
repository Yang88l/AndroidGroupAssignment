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
    private dbmanager_login_history dbmanager_login_history;
    private dbmanager_book_summary dbmanager_book_summary;
    private dbmanager_plan_summary dbmanager_plan_summary;
    private dbmanager_choose_airline dbmanager_choose_airline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_flight);

        //get date
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        Cursor cursor = dbmanager_flight.fetch(getUserID());
        String date = cursor.getString(3);
        cursor.close();
        dbmanager_flight.close();
        main.updateVersion();

        TextView text = findViewById(R.id.flight_information_text);
        text.setText("You have made a flight booking on " + date);
    }

    public void finish(View view) {
        //get activity
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        String activity = cursor_login.getString(2);
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();

        if (activity.equals("plan")) {
            dbmanager_plan_summary = new dbmanager_plan_summary(this);
            dbmanager_plan_summary.open();
            dbmanager_plan_summary.insert("flight", getAirlineID(), getUserID());
            dbmanager_plan_summary.close();
            main.updateVersion();
            Intent intent = new Intent(this,planning_summary.class);
            startActivity(intent);
        }
        else if (activity.equals("book")) {
            dbmanager_book_summary = new dbmanager_book_summary(this);
            dbmanager_book_summary.open();
            dbmanager_book_summary.insert("flight", getAirlineID(), getUserID());
            dbmanager_book_summary.close();
            main.updateVersion();
            Intent intent = new Intent(this,booking_summary.class);
            startActivity(intent);
        }
    }

    public int getUserID(){
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        int user_id=Integer.parseInt(cursor_login.getString(1));
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();
        return user_id;
    }

    public int getAirlineID(){
        dbmanager_choose_airline = new dbmanager_choose_airline(this);
        dbmanager_choose_airline.open();
        Cursor cursor_airline = dbmanager_choose_airline.fetch();
        cursor_airline.moveToLast();
        int airline_id=Integer.parseInt(cursor_airline.getString(1));
        cursor_airline.close();
        dbmanager_choose_airline.close();
        main.updateVersion();
        return airline_id;
    }
}