package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class state_flight extends AppCompatActivity {
    private com.example.groupassignment.dbmanager_flight dbmanager_flight;
    private  com.example.groupassignment.dbmanager_user dbmanager_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_flight);
    }

    public void sabah(View view) {
        chooseState("Sabah");
    }

    public void sarawak(View view) {
        chooseState("Sarawak");
    }

    public void johor(View view) {
        chooseState("Johor");
    }

    public void penang(View view) {
        chooseState("Penang");
    }

    public void chooseState(String state) {
        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(1);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        cursor.close();
        dbmanager_user.close();
        main.updateVersion();
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        dbmanager_flight.insert(null,null,null, state, user_id);
        dbmanager_flight.close();
        main.updateVersion();
        Intent intent = new Intent(state_flight.this, airline.class);
        startActivity(intent);
    }
}