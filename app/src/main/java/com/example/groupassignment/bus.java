package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class bus extends AppCompatActivity {

    private  com.example.groupassignment.dbmanager_bus dbmanager_bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus);

        dbmanager_bus = new dbmanager_bus(this);
    }

    public void soutern(View view) {
        dbmanager_bus.open();
        dbmanager_bus.update(null, null, "Soutern", null);
        dbmanager_bus.close();

        Intent intent = new Intent(this,seat_bus.class);
        startActivity(intent);

    }

    public void mayang_sari(View view) {
        dbmanager_bus.open();
        dbmanager_bus.update(null, null, "Mayang Sari", null);
        dbmanager_bus.close();

        Intent intent = new Intent(this,seat_bus.class);
        startActivity(intent);
    }

    public void city_express(View view) {
        dbmanager_bus.open();
        dbmanager_bus.update(null, null, "City Express", null);
        dbmanager_bus.close();

        Intent intent = new Intent(this,seat_bus.class);
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
}