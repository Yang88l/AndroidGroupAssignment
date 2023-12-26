package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class terms_conditions extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_terms_conditions dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_conditions);

        TextView Terms_title1 = findViewById(R.id.textView);
        TextView Terms_title2 = findViewById(R.id.textView15);
        TextView Terms_title3 = findViewById(R.id.textView9);
        TextView Terms_description1 = findViewById(R.id.textView4);
        TextView Terms_description2 = findViewById(R.id.textView5);
        TextView Terms_description3 = findViewById(R.id.textView6);
        TextView Terms_description4 = findViewById(R.id.textView8);

        dbmanager = new com.example.groupassignment.dbmanager_terms_conditions(this);
        dbmanager.open();
        Cursor cursor = dbmanager.fetch();

        String title = "";
        String description = "";

        cursor.moveToLast();
        title = cursor.getString(2);
        description = cursor.getString(3);
        cursor.close();

        Terms_title1.setText(title);
        Terms_title2.setText(title);
        Terms_title3.setText(title);
        Terms_description1.setText(description);
        Terms_description2.setText(description);
        Terms_description3.setText(description);
        Terms_description4.setText(description);

        dbmanager.close();
    }

    public void OK(View view) {
        Intent intent = new Intent(this,sign_up.class);
        startActivity(intent);
    }
}