package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class accommodation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accommodation);
    }

    public void notification(View view) {
        Intent intent = new Intent (this, notification.class);
        startActivity(intent);
    }

    public void like(View view) {
        //like button
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

    public void bovelord_hotel(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "hotel");
        intent.putExtra("_id", 1);
        startActivity(intent);
    }

    public void crystal_hotel(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "hotel");
        intent.putExtra("_id", 2);
        startActivity(intent);
    }

    public void ocean_hotel(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "hotel");
        intent.putExtra("_id", 3);
        startActivity(intent);
    }
}