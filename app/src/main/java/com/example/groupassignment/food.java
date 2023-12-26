package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class food extends AppCompatActivity {
    private dbmanager_choose_food dbmanager_choose_food;
    private Button mr_wu;
    public int user_id=1, food_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);

        dbmanager_choose_food = new dbmanager_choose_food(this);

        mr_wu = findViewById(R.id.mr_wu);
    }
    public void mr_wu(View view) {
        food_id=1;
        dbmanager_choose_food.open();
        dbmanager_choose_food.insert(food_id, user_id);
        dbmanager_choose_food.close();
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
}