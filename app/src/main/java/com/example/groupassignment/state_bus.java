package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class state_bus extends AppCompatActivity {

    private dbmanager_choose_bus dbmanager_choose_bus;
    private com.example.groupassignment.dbmanager_user dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_bus);
    }
    public void selangor(View view) {
        chooseState("Selangor");
    }

    public void perak(View view) {
        chooseState("Perak");
    }

    public void penang(View view) {
        chooseState("Penang");
    }

    public void johor(View view) {
        chooseState("Johor");
    }

    public void chooseState (String state) {
        dbhelper_login_history.DB_VERSION = main.dbversion++;
        dbmanager_login_history = new dbmanager_user(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch(1);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(1));
        dbmanager_login_history.close();

        dbhelper_choose_bus.DB_VERSION = main.dbversion++;
        dbmanager_choose_bus = new dbmanager_choose_bus(this);
        dbmanager_choose_bus.open();
        dbmanager_choose_bus.insert(user_id, state);
        dbmanager_choose_bus.close();
        startActivity(new Intent(this, bus.class));
    }
}