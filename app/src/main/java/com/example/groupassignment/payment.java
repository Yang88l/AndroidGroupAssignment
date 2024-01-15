package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.dbmanagers.dbmanager_book_history;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_user;

public class payment extends AppCompatActivity {
    private TextView price_text;
    private com.example.groupassignment.dbmanagers.dbmanager_book_history dbmanager_book_history;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private com.example.groupassignment.dbmanagers.dbmanager_user dbmanager_user;
    private Intent intent;
    private EditText password;
    public int user_id;
    public double price, totalPriceSum;
    public String user_password, input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);

        //ASSIGN ID
        price_text = findViewById(R.id.price_text);
        password = findViewById(R.id.password);

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

        //get user password
        dbmanager_user.open();
        Cursor cursor3 = dbmanager_user.fetch(user_id);
        user_password = cursor.getString(4);
        cursor3.close();
        dbmanager_user.close();
        main.updateVersion();

        //SET TEXT
        price_text.setText("RM " + Double.toString(totalPriceSum));
    }
    public void pay(View view) {
        input_password = password.getText().toString();
        if (input_password.equals(user_password)) {
            Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, payment_successful.class));
        } else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
    public void notification(View view) { startActivity(new Intent(this, notification.class));}
    public void home(View view) {
        startActivity(new Intent(this, main.class));
    }

    public void heart(View view) {
        startActivity(new Intent(this, my_favourite.class));
    }

    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }

    public void profile(View view) {
        startActivity(new Intent(this, profile.class));
    }
}