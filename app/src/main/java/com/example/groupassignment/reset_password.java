package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class reset_password extends AppCompatActivity {
    private com.example.groupassignment.dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
    }

    public void reset(View view) {
        String password = ((EditText) findViewById(R.id.editTextTextPassword4)).getText().toString();
        String password2 = ((EditText) findViewById(R.id.editTextTextPassword5)).getText().toString();
        if (password.equals("")||password2.equals("")) {
            Toast.makeText(this, "Please fill in all the blank!", Toast.LENGTH_LONG).show();
        }
        else if (password.equals(password2)) {
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
            dbmanager_user.update(user_id, null, null, null, null, password, null);
            dbmanager_user.close();

            Intent intent = new Intent (this, settings.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "The password is not matched", Toast.LENGTH_SHORT).show();
        }
    }
}