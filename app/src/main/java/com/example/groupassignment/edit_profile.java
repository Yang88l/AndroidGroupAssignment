package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class edit_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
    }


    public void cancel_edit(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void save_edit(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }
}