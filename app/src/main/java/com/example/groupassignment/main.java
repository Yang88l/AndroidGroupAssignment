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
    }

    public void notification(View view) {
        Intent intent = new Intent(this,notification.class);
        startActivity(intent);
    }

    public void plan(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int login_id=Integer.parseInt(cursor.getString(0));
        dbmanager_login_history.update(login_id, "plan", "logged out");
        dbmanager_login_history.close();
        Intent intent = new Intent(this,select_location.class);
        startActivity(intent);
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
        Intent intent = new Intent(this, my_favourite.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);
    }

    public void profile(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        String status=cursor.getString(3);
        dbmanager_login_history.close();
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        if (status.equals("logged out")) {
            Intent intent = new Intent(this, log_in.class);
            startActivity(intent);
        }
        else if (status.equals("logged in")) {
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
        }
    }
}