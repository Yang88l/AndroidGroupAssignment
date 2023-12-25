package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class airline extends AppCompatActivity {
    private dbmanager_choose_airline dbmanager_choose_airline;
    private Button button;
    private Button button2;
    private Button button3;
    public int input, user_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airline);

        dbmanager_choose_airline = new dbmanager_choose_airline(this);

        button = findViewById(R.id.airasia);
        button.setTag("AirAsia");
        button2 = findViewById(R.id.firefly);
        button2.setTag("FireFly");
        button3 = findViewById(R.id.berjaya_air);
        button3.setTag("Berjaya Air");




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String airlineName = (String) button.getTag();
                Intent intent = new Intent(airline.this, date_flight.class);
                intent.putExtra("AirAsia",airlineName.toString());
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String airlineName2 = (String) button2.getTag();
                Intent intent = new Intent(airline.this, date_flight.class);
                intent.putExtra("FireFly",airlineName2.toString());
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String airlineName3 = (String) button3.getTag();
                Intent intent = new Intent(airline.this, date_flight.class);
                intent.putExtra("Berjaya_Air",airlineName3.toString());
                startActivity(intent);
            }
        });
    }
    public void airasia(View view) {
        //get user id
        //get airline_id
        input = 1;

        dbmanager_choose_airline.open();
        dbmanager_choose_airline.insert(input, user_id);
        dbmanager_choose_airline.close();
    }
    public void firefly(View view) {
        //get user id
        //get airline_id
        input = 2;

        dbmanager_choose_airline.open();
        dbmanager_choose_airline.insert(input, user_id);
        dbmanager_choose_airline.close();
    }
    public void berjaya_air(View view) {
        //get user id
        //get airline_id
        input = 3;

        dbmanager_choose_airline.open();
        dbmanager_choose_airline.insert(input, user_id);
        dbmanager_choose_airline.close();
    }
}