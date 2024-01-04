package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class payment_successful extends AppCompatActivity {
    private TextView price_text;
    private Intent intent;
    private String price;
    private payment payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_successful);

        //ASSIGN ID
        price_text = findViewById(R.id.price_text);

        //GET VALUE
        payment = new payment();
        price = String.valueOf(payment.totalPriceSum);

        //SET TEXT
        price_text.setText("RM" + price);
    }

    //FINISH BUTTON
    public void finish(View view) {
        Intent intent = new Intent(payment_successful.this, main.class);
        startActivity(intent);
    }

  
}