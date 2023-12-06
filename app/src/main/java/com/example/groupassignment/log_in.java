package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class log_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
    }

    public void log_in(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void sign_up_here(View view) {
        Intent intent = new Intent (this, sign_up.class);
        startActivity(intent);
    }
}