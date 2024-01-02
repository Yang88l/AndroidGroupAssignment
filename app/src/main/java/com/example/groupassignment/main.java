package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class main extends AppCompatActivity {
    public static int dbversion = 1;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;
    private com.example.groupassignment.dbmanager_user dbmanager_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        dbmanager_login_history.insert(1, "logged out", "null");
        dbmanager_login_history.close();
    }


    public void plan(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int login_id=Integer.parseInt(cursor.getString(0));
        String status=cursor.getString(3);
        if (status.equals("logged out")) {
            dbmanager_login_history.update(login_id, "plan", "logged out");
            dbmanager_login_history.close();
            Intent intent = new Intent(this,select_location.class);
            startActivity(intent);
        }
        dbmanager_login_history.close();
    }

    public void Book(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int login_id=Integer.parseInt(cursor.getString(0));
        String status=cursor.getString(3);
        if (status.equals("logged in")) {
            dbmanager_login_history.update(login_id, "book", "logged in");
            dbmanager_login_history.close();
            Intent intent = new Intent(this,select_location.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "You are not logged in.", Toast.LENGTH_SHORT).show();
            dbmanager_login_history.close();
        }

    }
    public void favourite(View view) {
        startActivity(new Intent(this, my_favourite.class));
    }
    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }
    public void profile(View view) {
        startActivity(new Intent(this, profile.class));
    }
    public void notification(View view) {
        startActivity(new Intent(this, notification.class));
    }
}