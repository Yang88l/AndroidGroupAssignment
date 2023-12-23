package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class payment_method extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_method);
    }

    //TOUCH N GO
    public void touchngo(View view) {
    Intent intent = new Intent(payment_method.this, payment.class);
    startActivity(intent);
    }

    //BOTTOM BUTTONS DIRECTORY
    public void home(View view) {
        Intent intent = new Intent(payment_method.this, main.class);
        startActivity(intent);
    }
    public void heart(View view) {
        Intent intent = new Intent(payment_method.this, my_favourite.class);
        startActivity(intent);
    }
    public void history(View view) {
        Intent intent = new Intent(payment_method.this, book_history.class);
        startActivity(intent);
    }
    public void profile(View view) {
        Intent intent = new Intent(payment_method.this, profile.class);
        startActivity(intent);
    }
}