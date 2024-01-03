package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class my_favourite extends AppCompatActivity {
    private dbmanager_favourite dbmanager_favourite;
    private dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_favourite);

        TextView text1 = findViewById(R.id.text_1);
        TextView text2 = findViewById(R.id.textView);
        TextView text3 = findViewById(R.id.textView2);
        TextView text4 = findViewById(R.id.textView3);

        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        Cursor cursor = dbmanager_favourite.fetch();

        String name = "";
        String reviews = "";
        int ticket_sold = 0;
        double price = 0;

        cursor.moveToLast();
        name = cursor.getString(2);
        reviews = cursor.getString(2);
        ticket_sold = Integer.parseInt(cursor.getString(2));
        price = Double.parseDouble(cursor.getString(2));
        cursor.close();

        text1.setText(name);
        text2.setText(reviews);
        text3.setText(ticket_sold + "Ticket Sold");
        text4.setText(String.format("RM%.2f", price));

        dbmanager_favourite.close();
    }
    public void profile(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        String status=cursor.getString(3);
        cursor.close();
        dbmanager_login_history.close();
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        if (status.equals("logged out")) {
            Intent intent = new Intent(this, log_in.class);
            startActivity(intent);
        }
        else if (status.equals("logged in")) {
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
        }
    }
    public void notification(View view) {
        startActivity(new Intent(this, notification.class));
    }
    public void heart(View view) {
        startActivity(new Intent(this, my_favourite.class));
    }
    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }

}