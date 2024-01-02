package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class sign_up_login extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);
        /*Intent intent = getIntent();
        int user_id = Integer.parseInt(intent.getStringExtra("USER_ID"));
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        dbmanager_login_history.insert(3, "logged in", "null");
        dbmanager_login_history.close();
        Toast.makeText(this, "Your information has saved to database", Toast.LENGTH_SHORT).show();
    */
    }

}