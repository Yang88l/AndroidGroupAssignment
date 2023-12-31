package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class log_in extends AppCompatActivity {
    private dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
    }

    public void log_in(View view) {
        String name = ((EditText) findViewById(R.id.input_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.input_password)).getText().toString();

        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetchByName(name);
        int user_id=Integer.parseInt(cursor.getString(0));
        String passwd=cursor.getString(5);
        cursor.close();
        dbmanager_user.close();
        main.updateVersion();

        if (password.equals(passwd)) {
            dbmanager_login_history = new dbmanager_login_history(this);
            dbmanager_login_history.open();
            dbmanager_login_history.insert(user_id, "logged in", "null");
            dbmanager_login_history.close();
            main.updateVersion();
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void sign_up_here(View view) {
        Intent intent = new Intent (this, sign_up.class);
        startActivity(intent);
    }
}