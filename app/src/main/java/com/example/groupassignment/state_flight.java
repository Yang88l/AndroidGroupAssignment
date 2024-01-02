package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class state_flight extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;

    private com.example.groupassignment.dbmanager_flight dbmanager_flight;
    private  com.example.groupassignment.dbmanager_user dbmanager_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_flight);
    }

    public void sabah(View view) {
        dbhelper_user.DB_VERSION = main.dbversion++;
        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(1);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        dbmanager_user.close();
        String state = "Sabah";
        dbhelper_flight.DB_VERSION = main.dbversion++;
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        dbmanager_flight.insert(null,null,null, state,user_id);
        dbmanager_flight.close();
        Intent intent = new Intent(state_flight.this, airline.class);
        startActivity(intent);
    }

    public void sarawak(View view) {
        dbhelper_user.DB_VERSION = main.dbversion++;
        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(1);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        dbmanager_user.close();
        String state = "Sarawak";
        dbhelper_flight.DB_VERSION = main.dbversion++;
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        dbmanager_flight.insert(null,null,null, state,user_id);
        dbmanager_flight.close();
        Intent intent = new Intent(state_flight.this, airline.class);
        startActivity(intent);
    }

    public void johor(View view) {
        dbhelper_user.DB_VERSION = main.dbversion++;
        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(1);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        dbmanager_user.close();
        String state = "Johor";
        dbhelper_flight.DB_VERSION = main.dbversion++;
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        dbmanager_flight.insert(null,null, null, state,user_id);
        dbmanager_flight.close();
        Intent intent = new Intent(state_flight.this, airline.class);
        startActivity(intent);
    }

    public void penang(View view) {
        dbhelper_user.DB_VERSION = main.dbversion++;
        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(1);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        dbmanager_user.close();
        String state = "Penang";
        dbhelper_flight.DB_VERSION = main.dbversion++;
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        dbmanager_flight.insert(null,null,null, state,user_id);
        dbmanager_flight.close();
        Intent intent = new Intent(state_flight.this, airline.class);
        startActivity(intent);
    }
}