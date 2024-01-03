package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class payment extends AppCompatActivity {
    private TextView price_text;
    private dbmanager_book_history dbmanager_book_history;
    private dbmanager_login_history dbmanager_login_history;
    private dbmanager_user dbmanager_user;
    private Intent intent;
    private EditText pin;
    public int user_id, account_pin, input_pin;
    public double price, totalPriceSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        //ASSIGN ID
        price_text = findViewById(R.id.price_text);
        pin = findViewById(R.id.pin);

        //GET VALUE
        Intent intent = new Intent(getIntent());

        dbmanager_book_history = new dbmanager_book_history(this);
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_user = new dbmanager_user(this);

        //identify account
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        user_id = cursor.getInt(1);
        cursor.close();
        dbmanager_login_history.close();
        main.updateVersion();

         //get price of user order
        dbmanager_book_history.open();
        Cursor cursor2 = dbmanager_book_history.fetch(user_id);
        price = cursor.getDouble(3);

         //assuming price stores multiple value
        double[] total_price = new double[] {price};

        //sum up all price
        for (double price : total_price) {
        totalPriceSum += price;
        }
       dbmanager_book_history.close();
        main.updateVersion();
        cursor2.close();

        //get user pin
        dbmanager_user.open();
        Cursor cursor3 = dbmanager_user.fetch(user_id);
        account_pin = cursor.getInt(6);
        cursor3.close();
        dbmanager_user.close();
        main.updateVersion();

        //SET TEXT
        price_text.setText(Double.toString(totalPriceSum));
    }
    public void pay(View view) {
        input_pin = Integer.parseInt(pin.getText().toString());
        if (input_pin == account_pin) {
            Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, payment_successful.class));
        } else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}