package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class set_picture extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_picture);

    }

    public void camera(View view) {
        //open camera
    }

    public void profile1(View view) {
        Intent intent = new Intent(this,edit_profile.class);
        startActivity(intent);
    }

    public void profile2(View view) {
        Intent intent = new Intent(this,edit_profile.class);
        startActivity(intent);
    }

    public void profile3(View view) {
        Intent intent = new Intent(this,edit_profile.class);
        startActivity(intent);
    }

    public void profile4(View view) {
        Intent intent = new Intent(this,edit_profile.class);
        startActivity(intent);
    }

    public void profile5(View view) {
        Intent intent = new Intent(this,edit_profile.class);
        startActivity(intent);
    }
}