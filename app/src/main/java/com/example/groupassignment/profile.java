package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_user dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        TextView title = findViewById(R.id.textView);
        TextView Name = findViewById(R.id.textView3);
        TextView phone_number = findViewById(R.id.textView7);
        TextView email_address = findViewById(R.id.textView16);
        TextView birthday_date = findViewById(R.id.textView17);
        TextView point = findViewById(R.id.textView19);
        ImageView user_picture = findViewById(R.id.imageView);

        dbmanager = new com.example.groupassignment.dbmanager_user(this);
        dbmanager.open();
        Cursor cursor = dbmanager.fetch();

        String name = "";
        String email = "";
        String phone = "";
        String birthday = "";
        String picture = "";

        cursor.moveToLast();
        name = cursor.getString(2);
        email = cursor.getString(3);
        phone = cursor.getString(4);
        birthday = cursor.getString(5);
        picture = cursor.getString(7);
        cursor.close();

        title.setText(name);
        Name.setText(name);
        phone_number.setText(phone);
        email_address.setText(email);
        birthday_date.setText(birthday);
        user_picture.setImageURI(Uri.parse(picture));

        dbmanager.close();
    }

    public void settings(View view) {
        Intent intent = new Intent (this, settings.class);
        startActivity(intent);
    }

    public void edit_profile(View view) {
        Intent intent = new Intent (this, edit_profile.class);
        startActivity(intent);
    }

    public void log_out(View view) {
        Intent intent = new Intent (this, log_in.class);
        startActivity(intent);
    }
}