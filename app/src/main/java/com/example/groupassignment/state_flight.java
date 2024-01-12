package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.groupassignment.dbmanagers.dbmanager_flight;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;

public class state_flight extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_flight dbmanager_flight;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_flight);

        //Top Navigation
        BaseActivity.setupToolbar(this);
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
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        dbmanager_flight.insert(null,null,null, state, getUserID());
        dbmanager_flight.close();
        main.updateVersion();
        Intent intent = new Intent(state_flight.this, airline.class);
        startActivity(intent);
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

    public void notification(View view) { startActivity(new Intent(this, notification.class));}
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
}