package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.groupassignment.dbmanagers.dbmanager_message;

public class message extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_message dbmanager_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        TextView text1 = findViewById(R.id.text_1);
        TextView text2 = findViewById(R.id.text_2);
        TextView text3 = findViewById(R.id.text_3);
        TextView text4 = findViewById(R.id.text_4);

        dbmanager_message = new dbmanager_message(this);
        dbmanager_message.open();
        Cursor cursor = dbmanager_message.fetch();

        String message = "";

        cursor.moveToLast();
        message = cursor.getString(2);
        cursor.close();

        text1.setText(message);
        text2.setText(message);
        text3.setText(message);
        text4.setText(message);

        dbmanager_message.close();
        main.updateVersion();
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
        startActivity(new Intent(this, profile.class));
    }

}