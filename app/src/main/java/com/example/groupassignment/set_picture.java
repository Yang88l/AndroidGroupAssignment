package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class set_picture extends AppCompatActivity {
    private dbmanager_user dbmanager_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_picture);
    }

    public void camera(View view) {
        //open camera
    }

    public void profile1(View view) {
        setProfile("profile1");
    }

    public void profile2(View view) {
        setProfile("profile2");
    }

    public void profile3(View view) {
        setProfile("profile3");
    }

    public void profile4(View view) {
        setProfile("profile4");
    }

    public void profile5(View view) {
        setProfile("profile5");
    }

    public void setProfile(String picture){
        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetchALL();
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        cursor.close();
        dbmanager_user.update(user_id, null, null, null, null, null, picture);
        dbmanager_user.close();
        main.updateVersion();
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }
}