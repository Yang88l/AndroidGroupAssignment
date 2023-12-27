package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class booking_summary extends AppCompatActivity {
    private dbmanager_book_summary dbmanager_book_summary;
    private dbhelper_book_summary dbhelper_book_summary;
    private int user_id=1;
    private TextView booking_summary_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_summary);

        dbmanager_book_summary = new dbmanager_book_summary(this);
        dbhelper_book_summary = new dbhelper_book_summary(this);

        booking_summary_text = findViewById(R.id.booking_summary_text);

        // Get a readable database
        SQLiteDatabase database = dbhelper_book_summary.getReadableDatabase();

        // Call the method to fetch data with INNER JOIN and pass the user_id
        Cursor cursor = dbmanager_book_summary.fetchInnerJoin(user_id);

        // Get column indices
        int hotel_idIndex = cursor.getColumnIndex(dbhelper_book_summary.HOTEL_ID);
        int airline_idIndex = cursor.getColumnIndex(dbhelper_book_summary.AIRLINE_ID);
        int food_idIndex = cursor.getColumnIndex(dbhelper_book_summary.FOOD_ID);
        int transport_idIndex = cursor.getColumnIndex(dbhelper_book_summary.TRANSPORT_ID);

        int hotel_nameIndex = cursor.getColumnIndex(dbhelper_accomodation_info.HOTEL_NAME);
        int airlineIndex = cursor.getColumnIndex(dbhelper_airline_info.AIRLINE);
        int foodIndex = cursor.getColumnIndex(dbhelper_food_info.FOOD);
        int total_priceIndex = cursor.getColumnIndex(dbhelper_book_summary.TOTAL_PRICE);
        int transportIndex = cursor.getColumnIndex(dbhelper_transport_info.TRANSPORT);

        // retrieve data if got database
        if (cursor != null && hotel_idIndex >= 0 && airline_idIndex >= 0 && food_idIndex >= 0 && transport_idIndex >= 0 && total_priceIndex >= 0
                && hotel_nameIndex >= 0 && airlineIndex >= 0 && foodIndex >= 0 && transportIndex >= 0) {
            while (cursor.moveToNext()) {
                // Access data using cursor.getColumnIndex
                int hotel_id = cursor.getInt(hotel_idIndex);
                int airline_id = cursor.getInt(airline_idIndex);
                int food_id = cursor.getInt(food_idIndex);
                int transport_id = cursor.getInt(transport_idIndex);

                double total_price = cursor.getDouble(total_priceIndex);
                String hotel_name = cursor.getString(hotel_nameIndex);
                String airline = cursor.getString(airlineIndex);
                String food = cursor.getString(foodIndex);
                String transport = cursor.getString(transportIndex);


                // display
                booking_summary_text.setText("Hotel Name: " + hotel_name + "\n" +
                                             "Food: " + food + "\n" +
                                             "Airline: " +  airline + "\n" +
                                             "Transport: " +  transport + "\n" +
                                             "Total Price: " + total_price);
            }
            cursor.close();
        }
        database.close();
    }

    public void notification(View view) {
        Intent intent = new Intent (this, notification.class);
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

    public void ok(View view) {
        Intent intent = new Intent(this, main.class);
        startActivity(intent);
    }
}