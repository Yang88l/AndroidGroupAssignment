package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class book_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_history);
    }

    public void home(View view) {
        Intent intent = new Intent (this, main.class);
        startActivity(intent);
    }

    public void heart(View view) {
        Intent intent = new Intent (this, my_favourite.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent (this, book_history.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }
}