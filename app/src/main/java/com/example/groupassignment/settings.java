package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    public void back_settings(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void reset_password(View view) {
    }

    public void delete_account(View view) {
    }
}