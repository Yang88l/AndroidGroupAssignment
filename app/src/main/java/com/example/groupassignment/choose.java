package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class choose extends AppCompatActivity {

    private dbmanager_choose_accomodation dbmanager_choose_accomodation;
    private dbhelper_choose_accomodation dbhelper_choose_accomodation;
    private int user_id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
/*
        dbmanager_choose_accomodation = new dbmanager_choose_accomodation(this);
        dbhelper_choose_accomodation = new dbhelper_choose_accomodation(this);


        // Get a readable database
        SQLiteDatabase database = dbhelper_choose_accomodation.getReadableDatabase();

        // Call the method to fetch data with INNER JOIN and pass the user_id
        Cursor cursor = dbmanager_choose_accomodation.fetchInnerJoin(user_id);

        // Get column indices
        int airline_idIndex = cursor.getColumnIndex(dbhelper_book_summary.AIRLINE_ID);
        int food_idIndex = cursor.getColumnIndex(dbhelper_book_summary.FOOD_ID);
        int transport_idIndex = cursor.getColumnIndex(dbhelper_book_summary.TRANSPORT_ID);

        int airlineIndex = cursor.getColumnIndex(dbhelper_airline_info.AIRLINE);
        int foodIndex = cursor.getColumnIndex(dbhelper_food_info.FOOD);
        int transportIndex = cursor.getColumnIndex(dbhelper_transport_info.TRANSPORT);
        int total_priceIndex = cursor.getColumnIndex(dbhelper_book_summary.TOTAL_PRICE);

        // retrieve data if got database
        if (cursor != null && airline_idIndex >= 0 && food_idIndex >= 0 && transport_idIndex >= 0 && total_priceIndex >= 0 && airlineIndex >= 0 && foodIndex >= 0 && transportIndex >= 0) {
            while (cursor.moveToNext()) {
                // Access data using cursor.getColumnIndex
                int airline_id = cursor.getInt(airline_idIndex);
                int food_id = cursor.getInt(food_idIndex);
                int transport_id = cursor.getInt(transport_idIndex);

                double total_price = cursor.getDouble(total_priceIndex);
                String airline = cursor.getString(airlineIndex);
                String food = cursor.getString(foodIndex);
                String transport = cursor.getString(transportIndex);
            }
            cursor.close();
        }
        database.close();*/
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

    public void notification(View view) {
        Intent intent = new Intent (this, notification.class);
        startActivity(intent);
    }
}