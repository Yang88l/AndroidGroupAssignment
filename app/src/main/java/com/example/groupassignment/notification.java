package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.groupassignment.dbmanagers.dbmanager_notification;

public class notification extends AppCompatActivity {

    private com.example.groupassignment.dbmanagers.dbmanager_notification dbmanager_notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        TextView text1 = findViewById(R.id.text_1);
        TextView text2 = findViewById(R.id.text_2);
        TextView text3 = findViewById(R.id.text_3);
        TextView text4 = findViewById(R.id.text_4);

        dbmanager_notification = new dbmanager_notification(this);
        dbmanager_notification.open();
        Cursor cursor = dbmanager_notification.fetch();

        String notification = "";

        cursor.moveToLast();
        notification = cursor.getString(2);
        cursor.close();

        text1.setText(notification);
        text2.setText(notification);
        text3.setText(notification);
        text4.setText(notification);

        dbmanager_notification.close();
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

    public void message(View view) {
        Intent intent = new Intent(this,message.class);
        startActivity(intent);
    }

}