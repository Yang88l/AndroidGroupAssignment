package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class pax_flight extends AppCompatActivity {
    private com.example.groupassignment.dbmanager_pax dbmanager_pax;
    private  com.example.groupassignment.dbmanager_user dbmanager_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pax_flight);
    }

    public void buy(View view) {
        EditText ticket_amount = findViewById(R.id.ticket_amount);
        int amount = Integer.parseInt(ticket_amount.getText().toString());
        EditText adult_amount = findViewById(R.id.ticket_amount);
        int adult = Integer.parseInt(adult_amount.getText().toString());
        EditText kid_amount = findViewById(R.id.ticket_amount);
        int kid = Integer.parseInt(kid_amount.getText().toString());

        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(1);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        cursor.close();
        dbmanager_user.close();
        main.updateVersion();

        dbmanager_pax = new dbmanager_pax(this);
        dbmanager_pax.open();
        dbmanager_pax.insert(user_id, amount, adult, kid);
        dbmanager_pax.close();
        main.updateVersion();

        Intent intent = new Intent(pax_flight.this, info_flight.class);
        startActivity(intent);
    }


}
