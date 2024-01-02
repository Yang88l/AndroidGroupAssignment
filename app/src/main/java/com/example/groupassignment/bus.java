package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

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
    }

    public void mayang_sari(View view) {
        dbmanager_bus.open();
        dbmanager_bus.update(null, null, "Mayang Sari", null);
        dbmanager_bus.close();
    }

    public void city_express(View view) {
        dbmanager_bus.open();
        dbmanager_bus.update(null, null, "City Express", null);
        dbmanager_bus.close();
    }
}