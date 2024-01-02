package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_flight);
    }

    public void confirm(View view) {
        EditText date = findViewById(R.id.date);
        //get user id
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        int user_id=Integer.parseInt(cursor_login.getString(1));
        dbmanager_login_history.close();

        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        Cursor cursor = dbmanager_flight.fetch(1);
        cursor.moveToLast();
        dbmanager_flight.update(Integer.parseInt(cursor.getString(0)),null,null, String.valueOf(date),null,user_id);
        dbmanager_flight.close();
        Intent intent = new Intent(calendar_flight.this, time_flight.class);
        startActivity(intent);
    }
}