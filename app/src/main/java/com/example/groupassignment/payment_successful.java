package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class payment_successful extends AppCompatActivity {
    private TextView output_price;
    private Intent intent;

    private double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_successful);

        //ASSIGN ID
        output_price = findViewById(R.id.output_price);
    }



    //ASSIGN ID

    //SET TEXT TO ITEM PRICE
   // intent = getIntent();
   // price = intent.getDoubleExtra("output_price",0);
    //output_price.setText(price);

    //FINISH BUTTON
    public void finish(View view) {
        Intent intent = new Intent(payment_successful.this, main.class);
        startActivity(intent);
    }

    //BOTTOM BUTTONS DIRECTORY
    public void home(View view) {
        Intent intent = new Intent(payment_successful.this, main.class);
        startActivity(intent);
    }
    public void heart(View view) {
        Intent intent = new Intent(payment_successful.this, my_favourite.class);
        startActivity(intent);
    }
    public void history(View view) {
        Intent intent = new Intent(payment_successful.this, book_history.class);
        startActivity(intent);
    }
    public void profile(View view) {
        Intent intent = new Intent(payment_successful.this, profile.class);
        startActivity(intent);
    }
}