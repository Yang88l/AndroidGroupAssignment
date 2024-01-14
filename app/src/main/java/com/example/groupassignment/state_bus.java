package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.groupassignment.dbmanagers.dbmanager_choose_bus;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;

public class state_bus extends AppCompatActivity {

    private com.example.groupassignment.dbmanagers.dbmanager_choose_bus dbmanager_choose_bus;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_bus);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);

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
        dbmanager_choose_bus = new dbmanager_choose_bus(this);
        dbmanager_choose_bus.open();
        dbmanager_choose_bus.insert(getUserID(), state);
        dbmanager_choose_bus.close();
        main.updateVersion();
        startActivity(new Intent(this, bus.class));
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