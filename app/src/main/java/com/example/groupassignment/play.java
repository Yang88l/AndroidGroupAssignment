package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class play extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
    }
    public void add(View view) {
    }

    public void detail(View view) {
        Intent intent = new Intent(this, information.class);
        startActivity(intent);
    }

    public void sunway(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 1);
        startActivity(intent);
    }

    public void genting(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 2);
        startActivity(intent);
    }

    public void sky(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 3);
        startActivity(intent);
    }

    public void zoo(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 4);
        startActivity(intent);
    }
}