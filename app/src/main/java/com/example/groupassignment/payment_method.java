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


}