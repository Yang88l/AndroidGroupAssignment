package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class choose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

        //Top Navigation
        BaseActivity.setupToolbar(this);
    }

    public void play(View view) {
        Intent intent = new Intent (this, play.class);
        startActivity(intent);
    }

    public void hotel(View view) {
        Intent intent = new Intent (this, accommodation.class);
        startActivity(intent);
    }

    public void food(View view) {
        Intent intent = new Intent (this, food.class);
        startActivity(intent);
    }

    public void transport(View view) {
        Intent intent = new Intent (this, transport.class);
        startActivity(intent);
    }


    public void notification(View view) { startActivity(new Intent(this, notification.class));}
    public void home(View view) {
        startActivity(new Intent(this, main.class));
    }

    public void heart(View view) {
        startActivity(new Intent(this, my_favourite.class));
    }

    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }

    public void profile(View view) {
        startActivity(new Intent(this, profile.class));
    }
}