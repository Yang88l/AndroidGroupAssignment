package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class settings extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    public void reset_password(View view) {
        Intent intent = new Intent (this, reset_password.class);
        startActivity(intent);
    }

    public void delete_account(View view) {
        dbhelper_login_history.DB_VERSION = main.dbversion++;
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(1));
        dbmanager_login_history.close();

        dbhelper_user.DB_VERSION = main.dbversion++;
        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        dbmanager_user.delete(user_id);
        dbmanager_user.close();

        Intent intent = new Intent (this, main.class);
        startActivity(intent);
    }
}