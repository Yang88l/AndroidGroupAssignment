package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_user;

public class log_in extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
    }

    public void log_in(View view) {
        int user_id;
        String passwd;

        String name = ((EditText) findViewById(R.id.input_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.input_password)).getText().toString();

        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetchByName(name);
        if (name.equals("")||password.equals("")) {
            Toast.makeText(this, "Please fill in all the blanks", Toast.LENGTH_SHORT).show();
            cursor.close();
            dbmanager_user.close();
            main.updateVersion();
        }
        else if (cursor.getCount() == 0) {
            Toast.makeText(this, "Name not registered.", Toast.LENGTH_SHORT).show();
            cursor.close();
            dbmanager_user.close();
            main.updateVersion();
        }
        else {
            user_id=Integer.parseInt(cursor.getString(0));
            passwd=cursor.getString(5);
            cursor.close();
            dbmanager_user.close();
            main.updateVersion();
            if (password.equals(passwd)) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
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




    }

    public void sign_up_here(View view) {
        Intent intent = new Intent (this, sign_up.class);
        startActivity(intent);
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