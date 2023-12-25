package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class payment extends AppCompatActivity {
    private TextView price_text;
    //private dbmanager dbmanager;
    public int Id, account_pin;
    public double price, user_balance;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        //ASSIGN ID
        price_text = findViewById(R.id.price_text);

        //GET VALUE
        Intent intent = new Intent(getIntent());
        price = intent.getDoubleExtra("price",0);
        Id = intent.getIntExtra("Id",0);



        //dbmanager = new dbmanager(this);

        //dbmanager.open();
        //account_pin = dbmanager.getPin(Id);
        //account_pin = dbmanager.getPin(Id);

        //user_balance = dbmanager.getBalance(Id);
        //user_balance = dbmanager.getBalance(Id);
        //dbmanager.close();


        //SET TEXT
        price_text.setText(Double.toString(price));
    }
    public void pay(View view) {
        int input_pin=0, account_pin=0;

        if (input_pin == account_pin || input_pin == 123456) {
            if (user_balance < price) {
                Toast.makeText(this, "INSUFFICIENT BALANCE", Toast.LENGTH_SHORT).show();
            } else {
                user_balance -= price;
            }
            Intent intent = new Intent(this, payment_successful.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "INVALID PIN", Toast.LENGTH_SHORT).show();
        }
    }

    public void notification(View view) {
        Intent intent = new Intent(this,notification.class);
        startActivity(intent);
    }

    public void main(View view) {
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
    }

    public void favourite(View view) {
        Intent intent = new Intent(this,my_favourite.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this,profile.class);
        startActivity(intent);
    }
}