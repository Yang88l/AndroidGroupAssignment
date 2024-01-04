package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class transport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transport);
    }
    public void train(View view) {
        startActivity(new Intent(this, train_type.class));
    }
    public void bus(View view) {
        startActivity(new Intent(this, state_bus.class));
    }
    public void flight(View view) {
        startActivity(new Intent(this, state_flight.class));
    }


}


