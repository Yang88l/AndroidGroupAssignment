package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class food extends AppCompatActivity {
    private com.example.groupassignment.dbmanager_choose_food dbmanager_choose_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);

        dbhelper_choose_food.DB_VERSION = main.dbversion++;

        dbmanager_choose_food = new dbmanager_choose_food(this);

    }

    public void notification(View view) {
        Intent intent = new Intent (this, notification.class);
        startActivity(intent);
    }

    public void like(View view) {
        // like the food
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

    public void filter(View view) {
        Intent intent = new Intent (this, filter.class);
        startActivity(intent);
    }

    public void cielo_kl(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "food");
        intent.putExtra("_id", 1);
        startActivity(intent);
    }

    public void horizon_grill(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "food");
        intent.putExtra("_id", 2);
        startActivity(intent);
    }

    public void sky_bar(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "food");
        intent.putExtra("_id", 3);
        startActivity(intent);
    }
}