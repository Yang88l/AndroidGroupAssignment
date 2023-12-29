package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class set_picture extends AppCompatActivity {
    private com.example.groupassignment.dbmanager_user dbmanager_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_picture);

        dbmanager_user = new dbmanager_user(this);
    }

    public void camera(View view) {
        //open camera
    }

    public void profile1(View view) {
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(0);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        dbmanager_user.update(user_id, null, null, null, null, null, "profile1");
        dbmanager_user.close();
        Intent intent = new Intent(this,edit_profile.class);
        startActivity(intent);
    }

    public void profile2(View view) {
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(0);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        dbmanager_user.update(user_id, null, null, null, null, null, "profile2");
        dbmanager_user.close();
        Intent intent = new Intent(this,edit_profile.class);
        startActivity(intent);
    }

    public void profile3(View view) {
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(0);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        dbmanager_user.update(user_id, null, null, null, null, null, "profile3");
        dbmanager_user.close();
        Intent intent = new Intent(this,edit_profile.class);
        startActivity(intent);
    }

    public void profile4(View view) {
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(0);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        dbmanager_user.update(user_id, null, null, null, null, null, "profile4");
        dbmanager_user.close();
        Intent intent = new Intent(this,edit_profile.class);
        startActivity(intent);
    }

    public void profile5(View view) {
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetch(0);
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        dbmanager_user.update(user_id, null, null, null, null, null, "profile5");
        dbmanager_user.close();
        Intent intent = new Intent(this,edit_profile.class);
        startActivity(intent);
    }
}