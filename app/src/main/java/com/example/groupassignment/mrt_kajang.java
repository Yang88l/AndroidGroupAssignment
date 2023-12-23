package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mrt_kajang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mrt_kajang);
    }

    //BOTTOM BUTTONS DIRECTORY
    public void home(View view) {
        Intent intent = new Intent(mrt_kajang.this, main.class);
        startActivity(intent);
    }
    public void heart(View view) {
        Intent intent = new Intent(mrt_kajang.this, my_favourite.class);
        startActivity(intent);
    }
    public void history(View view) {
        Intent intent = new Intent(mrt_kajang.this, book_history.class);
        startActivity(intent);
    }
    public void profile(View view) {
        Intent intent = new Intent(mrt_kajang.this, profile.class);
        startActivity(intent);
    }
}