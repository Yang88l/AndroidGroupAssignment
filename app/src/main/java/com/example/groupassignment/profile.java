package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }

    public void settings(View view) {
        Intent intent = new Intent (this, settings.class);
        startActivity(intent);
    }

    public void edit_profile(View view) {
        Intent intent = new Intent (this, edit_profile.class);
        startActivity(intent);
    }

    public void log_out(View view) {
        Intent intent = new Intent (this, log_in.class);
        startActivity(intent);
    }
}