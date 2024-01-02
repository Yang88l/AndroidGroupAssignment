package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class seat_bus extends AppCompatActivity {
    private Button button;

    private dbmanager_bus dbmanager_bus;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;
    private com.example.groupassignment.dbmanager_plan_summary dbmanager_plan_summary;
    private com.example.groupassignment.dbmanager_book_summary dbmanager_book_summary;
    private com.example.groupassignment.dbmanager_choose_bus dbmanager_choose_bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_bus);

        dbmanager_bus = new dbmanager_bus(this);
    /*    button = findViewById(R.id.pickup);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(seat_bus.this, transport.class);
                startActivity(intent);
            }
        });*/
    }

    public void pick_up(View view) {
        //get activity
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        String activity = cursor_login.getString(2);

        //get user id
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(1));
        dbmanager_login_history.close();

        //get bus id
        dbmanager_choose_bus = new dbmanager_choose_bus(this);
        dbmanager_choose_bus.open();
        Cursor cursor_choose = dbmanager_choose_bus.fetch();
        cursor_choose.moveToLast();
        int bus_id=Integer.parseInt(cursor_choose.getString(1));
        dbmanager_choose_bus.close();

        Integer seat = Integer.valueOf(((EditText) findViewById(R.id.inputfrom5)).getText().toString());
        dbmanager_bus.open();
        Cursor cursor_bus = dbmanager_bus.fetch(bus_id);
        if (Integer.parseInt(cursor_bus.getString(0))<seat) {
            Toast.makeText(this, "Not Enough Seat", Toast.LENGTH_SHORT).show();
        }
        else {
            dbmanager_bus.update(bus_id, Integer.parseInt(cursor_bus.getString(0)) - seat, null);
        }
        dbmanager_bus.close();

        if (activity=="plan") {
            dbmanager_plan_summary = new dbmanager_plan_summary(this);
            dbmanager_plan_summary.open();
            dbmanager_plan_summary.insert("bus", bus_id, user_id);
            dbmanager_plan_summary.close();
            Intent intent = new Intent(this,planning_summary.class);
            startActivity(intent);
        }
        else if (activity=="book") {
            dbmanager_book_summary = new dbmanager_book_summary(this);
            dbmanager_book_summary.open();
            dbmanager_book_summary.insert("bus", bus_id, user_id);
            dbmanager_book_summary.close();
            Intent intent = new Intent(this,booking_summary.class);
            startActivity(intent);
        }
    }
}