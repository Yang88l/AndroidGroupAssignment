package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.dbmanagers.dbmanager_login_history;

public class payment_successful extends AppCompatActivity {
    private TextView price_text;
    private Intent intent;
    private String price;
    private payment payment;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_successful);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);

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
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        String status=cursor.getString(3);
        cursor.close();
        dbmanager_login_history.close();
        main.updateVersion();
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        if (status.equals("logged out")) {
            Intent intent = new Intent(this, log_in.class);
            startActivity(intent);
        }
        else if (status.equals("logged in")) {
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
        }
    }
  
}