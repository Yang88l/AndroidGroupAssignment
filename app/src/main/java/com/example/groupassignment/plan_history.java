package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class plan_history extends AppCompatActivity {

    private dbmanager_plan_history dbmanager_plan_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_history);

        TextView a = findViewById(R.id.a);
        TextView b = findViewById(R.id.b);
        TextView c = findViewById(R.id.c);
        TextView a2 = findViewById(R.id.a2);
        TextView b2 = findViewById(R.id.b2);
        TextView c2 = findViewById(R.id.c2);
        TextView a3 = findViewById(R.id.a3);
        TextView b3 = findViewById(R.id.b3);
        TextView c3 = findViewById(R.id.c3);
        TextView a4 = findViewById(R.id.a4);
        TextView b4 = findViewById(R.id.b4);
        TextView c4 = findViewById(R.id.c4);
        TextView price = findViewById(R.id.price_text1);
        TextView price2 = findViewById(R.id.price_text2);
        TextView price3 = findViewById(R.id.price_text3);
        TextView price4 = findViewById(R.id.price_text4);

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

        a.setText(location);
        c.setText(date);
        b.setText(status);
        price.setText(String.format("RM%.2f",cost));

        a2.setText(location);
        c2.setText(date);
        b2.setText(status);
        price2.setText(String.format("RM%.2f",cost));

        a3.setText(location);
        c3.setText(date);
        b3.setText(status);
        price3.setText(String.format("RM%.2f",cost));

        a4.setText(location);
        c4.setText(date);
        b4.setText(status);
        price4.setText(String.format("RM%.2f",cost));

        dbmanager_plan_history.close();
        main.updateVersion();
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

    public void heart(View view) {
        Intent intent = new Intent(this, my_favourite.class);
        startActivity(intent);    }

    public void book_history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);    }
}