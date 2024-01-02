package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class bus extends AppCompatActivity {

    private  com.example.groupassignment.dbmanager_bus dbmanager_bus;
    private com.example.groupassignment.dbmanager_choose_bus dbmanager_choose_bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus);

        dbmanager_bus = new dbmanager_bus(this);
    }

    public void soutern(View view) {
        chooseBus(1);
    }

    public void mayang_sari(View view) {
        chooseBus(2);
    }

    public void city_express(View view) {
        chooseBus(3);
    }

    public void notification(View view) {
        Intent intent = new Intent (this, notification.class);
        startActivity(intent);
    }
    public void main(View view) {
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this,profile.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);
    }

    public void heart(View view) {
        Intent intent = new Intent(this, my_favourite.class);
        startActivity(intent);
    }

    public void chooseBus(int bus_id) {
        dbhelper_choose_bus.DB_VERSION = main.dbversion++;
        dbmanager_choose_bus = new dbmanager_choose_bus(this);
        dbmanager_choose_bus.open();
        Cursor cursor_choose = dbmanager_choose_bus.fetch();
        cursor_choose.moveToLast();
        int _id=Integer.parseInt(cursor_choose.getString(0));

        dbmanager_choose_bus.update(_id, bus_id);
        dbmanager_choose_bus.close();

        Intent intent = new Intent(this,seat_bus.class);
        startActivity(intent);
    }
}