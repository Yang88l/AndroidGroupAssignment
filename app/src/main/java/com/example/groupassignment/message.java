package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;

public class message extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_message dbmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        TextView text1 = findViewById(R.id.text_1);
        TextView text2 = findViewById(R.id.text_2);
        TextView text3 = findViewById(R.id.text_3);
        TextView text4 = findViewById(R.id.text_4);

        dbmanager = new com.example.groupassignment.dbmanager_message(this);
        dbmanager.open();
        Cursor cursor = dbmanager.fetch();

        String message = "";

        cursor.moveToLast();
        message = cursor.getString(2);
        cursor.close();

        text1.setText(message);
        text2.setText(message);
        text3.setText(message);
        text4.setText(message);

        dbmanager.close();

    }
    public void home(View view) {
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
    }

    public void heart(View view) {
        Intent intent = new Intent(this,my_favourite.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this,profile.class);
        startActivity(intent);
    }

    public void notification(View view) {
        Intent intent = new Intent(this,notification.class);
        startActivity(intent);
    }
}