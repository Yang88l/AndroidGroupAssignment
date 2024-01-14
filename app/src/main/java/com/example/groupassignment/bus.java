package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.groupassignment.dbmanagers.dbmanager_choose_bus;

public class bus extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_choose_bus dbmanager_choose_bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);
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

    public void notification(View view) { startActivity(new Intent(this, notification.class));}
    public void home(View view) {
        startActivity(new Intent(this, main.class));
    }

    public void heart(View view) {
        startActivity(new Intent(this, my_favourite.class));
    }

    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }

    public void profile(View view) {
        startActivity(new Intent(this, profile.class));
    }

    public void chooseBus(int bus_id) {
        dbmanager_choose_bus = new dbmanager_choose_bus(this);
        dbmanager_choose_bus.open();
        Cursor cursor_choose = dbmanager_choose_bus.fetch();
        cursor_choose.moveToLast();
        int _id=Integer.parseInt(cursor_choose.getString(0));

        dbmanager_choose_bus.update(_id, bus_id);
        dbmanager_choose_bus.close();
        main.updateVersion();

        Intent intent = new Intent(this,seat_bus.class);
        startActivity(intent);
    }
}