package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class state_flight extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;

    private com.example.groupassignment.dbmanager_flight dbmanager_flight;
    private  com.example.groupassignment.dbmanager_user dbmanager_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_flight);

        button = findViewById(R.id.sabahbut);
        button2 = findViewById(R.id.sarawakbut);
        button3 = findViewById(R.id.penangbut);
        button4 = findViewById(R.id.johorbut);

        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_user = new dbmanager_user(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbmanager_user.open();
                Cursor cursor = dbmanager_user.fetch("user_id=0");
                cursor.moveToLast();
                int user_id=Integer.parseInt(cursor.getString(0));
                dbmanager_user.close();
                String state = "Sabah";
                dbmanager_flight.open();
                dbmanager_flight.insert(null,null,null, state,user_id);
                dbmanager_flight.close();
                Intent intent = new Intent(state_flight.this, airline.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbmanager_user.open();
                Cursor cursor = dbmanager_user.fetch("user_id=0");
                cursor.moveToLast();
                int user_id=Integer.parseInt(cursor.getString(0));
                dbmanager_user.close();
                String state = "Sarawak";
                dbmanager_flight.open();
                dbmanager_flight.insert(null,null,null, state,user_id);
                dbmanager_flight.close();
                Intent intent = new Intent(state_flight.this, airline.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbmanager_user.open();
                Cursor cursor = dbmanager_user.fetch("user_id=0");
                cursor.moveToLast();
                int user_id=Integer.parseInt(cursor.getString(0));
                dbmanager_user.close();
                String state = "Penang";
                dbmanager_flight.open();
                dbmanager_flight.insert(null,null,null, state,user_id);
                dbmanager_flight.close();
                Intent intent = new Intent(state_flight.this, airline.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbmanager_user.open();
                Cursor cursor = dbmanager_user.fetch("user_id=0");
                cursor.moveToLast();
                int user_id=Integer.parseInt(cursor.getString(0));
                dbmanager_user.close();
                String state = "Johor";
                dbmanager_flight.open();
                dbmanager_flight.insert(null,null, null, state,user_id);
                dbmanager_flight.close();
                Intent intent = new Intent(state_flight.this, airline.class);
                startActivity(intent);
            }
        });
    }
}