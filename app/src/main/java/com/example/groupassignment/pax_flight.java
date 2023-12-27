package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class pax_flight extends AppCompatActivity {
    private TextInputLayout ticket_amount, adult_amount, kid_amount;
    private com.example.groupassignment.dbmanager_pax dbmanager;
    private int user_id,amount,adult,kid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pax_flight);
/*
        ticket_amount = findViewById(R.id.ticket_amount);
        adult_amount = findViewById(R.id.adult_amount);
        kid_amount = findViewById(R.id.kid_amount);
*/
        user_id=1;
        amount=200;
        adult=3;
        kid=2;
        dbmanager = new dbmanager_pax(this);
        dbmanager.open();
        dbmanager.insert(user_id, amount, adult, kid);
        dbmanager.close();


    }





    //BUY BUTTON
    public void buy(View view) {
        Intent intent = new Intent(pax_flight.this, choose.class);
        startActivity(intent);


        intent = new Intent(pax_flight.this, planning_summary.class);
        intent.putExtra("ticket_amount", ticket_amount.getEditText().getText().toString());
        intent.putExtra("adult_amount", adult_amount.getEditText().getText().toString());
        intent.putExtra("kid_amount", kid_amount.getEditText().getText().toString());

    }

    //BOTTOM BUTTONS DIRECTORY
    public void home(View view) {
        Intent intent = new Intent(pax_flight.this, main.class);
        startActivity(intent);
    }
    public void heart(View view) {
        Intent intent = new Intent(pax_flight.this, my_favourite.class);
        startActivity(intent);
    }
    public void history(View view) {
        Intent intent = new Intent(pax_flight.this, book_history.class);
        startActivity(intent);
    }
    public void profile(View view) {
        Intent intent = new Intent(pax_flight.this, profile.class);
        startActivity(intent);
    }
    public void notification(View view) {
        Intent intent = new Intent(pax_flight.this, notification.class);
        startActivity(intent);
    }
}
