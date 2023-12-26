package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class my_favourite extends AppCompatActivity {

    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private com.example.groupassignment.dbmanager_favourite dbmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_favourite);

        button1 = findViewById(R.id.imageButton11);
        button2 = findViewById(R.id.imageButton8);
        button3 = findViewById(R.id.imageButton9);
        button4 = findViewById(R.id.imageButton10);

        TextView text1 = findViewById(R.id.text_1);
        TextView text2 = findViewById(R.id.textView);
        TextView text3 = findViewById(R.id.textView2);
        TextView text4 = findViewById(R.id.textView3);

        dbmanager = new com.example.groupassignment.dbmanager_favourite(this);
        dbmanager.open();
        Cursor cursor = dbmanager.fetch();

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

        dbmanager.close();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(my_favourite.this, main.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(my_favourite.this, my_favourite.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(my_favourite.this, plan_history.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(my_favourite.this, profile.class);
                startActivity(intent);
            }
        });
    }
}