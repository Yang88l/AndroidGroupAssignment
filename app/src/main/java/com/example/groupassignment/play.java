package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class play extends AppCompatActivity {
    private dbmanager_choose_play dbmanager_choose_play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        dbmanager_choose_play = new dbmanager_choose_play(this);
    }

    public void main(View view) {
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
    }

    public void favourite(View view) {
        Intent intent = new Intent(this,my_favourite.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this,profile.class);
        startActivity(intent);
    }

    public void add(View view) {
    }

    public void detail(View view) {
        Intent intent = new Intent(this, information.class);
        startActivity(intent);
    }

    public void filter(View view) {
        Intent intent = new Intent(this,filter.class);
        startActivity(intent);
    }

    public void notification(View view) {
        Intent intent = new Intent(this,notification.class);
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