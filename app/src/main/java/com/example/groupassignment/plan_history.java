package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class plan_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_history);
    }

    public void main(View view) {
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this,profile.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);    }

    public void favourite(View view) {
        Intent intent = new Intent(this, my_favourite.class);
        startActivity(intent);    }

    public void book_history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);    }
}