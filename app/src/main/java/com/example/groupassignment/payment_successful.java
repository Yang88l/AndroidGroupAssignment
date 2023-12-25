package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class payment_successful extends AppCompatActivity {
    private TextView price_text;
    ;
    private Intent intent;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_successful);

        //ASSIGN ID
        price_text = findViewById(R.id.price_text);

        //GET VALUE
        Intent intent = new Intent(getIntent());
        price = intent.getStringExtra("price");

        //SET TEXT
        price_text.setText("RM" + price);
    }

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

    public void notification(View view) {
        Intent intent = new Intent(payment_successful.this, notification.class);
        startActivity(intent);
    }
}