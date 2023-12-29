package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class state_bus extends AppCompatActivity {

    private dbmanager_bus dbmanager_bus;
    /*private Button button;
    private Button button2;
    private Button button3;
    private Button button4;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_bus);

        dbmanager_bus = new dbmanager_bus(this);

        /*button = findViewById(R.id.selangorbut);
        button2 = findViewById(R.id.perakbut);
        button3 = findViewById(R.id.penangbut);
        button4 = findViewById(R.id.johorbut);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(state_bus.this, seat_bus.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(state_bus.this, seat_bus.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(state_bus.this, seat_bus.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(state_bus.this, seat_bus.class);
                startActivity(intent);
            }
        });*/
    }
    public void selangor(View view) {
        String state = "Selangor";
        dbmanager_bus.open();
        dbmanager_bus.insert(null,null,state);
        dbmanager_bus.close();
        startActivity(new Intent(this, seat_bus.class));
    }

    public void perak(View view) {
        String state = "Perak";
        dbmanager_bus.open();
        dbmanager_bus.insert(null, null, state);
        dbmanager_bus.close();
        startActivity(new Intent(this, seat_bus.class));
    }

    public void penang(View view) {
        String state = "Penang";
        dbmanager_bus.open();
        dbmanager_bus.insert(null, null,state);
        dbmanager_bus.close();
        startActivity(new Intent(this, seat_bus.class));
    }

    public void johor(View view) {
        String state = "Johor";
        dbmanager_bus.open();
        dbmanager_bus.insert(null, null,state);
        dbmanager_bus.close();
        startActivity(new Intent(this, seat_bus.class));
    }
}