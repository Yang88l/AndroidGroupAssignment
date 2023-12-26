package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sign_up extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_user dbmanager;
    private String user_id,name,email,phone,birthday,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

    }

    public void sign_up(View view) {

        dbmanager = new dbmanager_user(this);
        dbmanager.open();
        dbmanager.insert( user_id, name, email, phone, birthday, password);
        dbmanager.close();

        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }
}