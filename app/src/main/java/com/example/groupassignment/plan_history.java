package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class plan_history extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_plan_history dbmanager_plan_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_history);

        TextView state_location = findViewById(R.id.text_state1);
        TextView plan_cost = findViewById(R.id.text_cost1);
        TextView plan_date = findViewById(R.id.text_date1);
        TextView plan_status = findViewById(R.id.text_status1);

        dbhelper_plan_history.DB_VERSION = main.dbversion++;
        dbmanager_plan_history = new dbmanager_plan_history(this);
        dbmanager_plan_history.open();
        Cursor cursor = dbmanager_plan_history.fetch();

        String location ="";
        double cost = 0;
        String date = "";
        String status = "";

        cursor.moveToLast();
        location = cursor.getString(2);
        cost = Double.parseDouble(cursor.getString(3));
        date = cursor.getString(4);
        status = cursor.getString(5);
        cursor.close();

        state_location.setText(location);
        plan_cost.setText(String.format("RM%.2f",cost));;
        plan_date.setText(date);
        plan_status.setText(status);

        dbmanager_plan_history.close();
    }

    public void main(View view) {
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this,profile.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);    }

    public void favourite(View view) {
        Intent intent = new Intent(this, my_favourite.class);
        startActivity(intent);    }

    public void book_history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);    }
}