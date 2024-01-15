package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_message;

public class message extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_message dbmanager_message;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);

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