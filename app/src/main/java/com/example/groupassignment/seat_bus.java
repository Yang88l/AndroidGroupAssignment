package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class seat_bus extends AppCompatActivity {
    private Button button;

    private dbmanager_bus dbmanager_bus;

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
        Integer seat = Integer.valueOf(((EditText) findViewById(R.id.inputfrom5)).getText().toString());
        dbmanager_bus.open();
        dbmanager_bus.insert( seat, null,null);
        dbmanager_bus.close();
    Intent intent = new Intent(seat_bus.this, transport.class);
    startActivity(intent);}
}