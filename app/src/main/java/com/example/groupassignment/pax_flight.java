package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_pax;
import com.example.groupassignment.dbmanagers.dbmanager_user;

public class pax_flight extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_pax dbmanager_pax;
    private com.example.groupassignment.dbmanagers.dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pax_flight);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);
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
        Cursor cursor = dbmanager_user.fetchALL();
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
