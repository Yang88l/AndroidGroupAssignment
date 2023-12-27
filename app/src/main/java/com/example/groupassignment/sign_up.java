package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_user dbmanager_user;
    //public String name,email,phone,birthday,password,picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        dbmanager_user = new dbmanager_user(this);
    }

    public void sign_up(View view) {
        String name="hong Yang";
        String email="hef@gmai9l.,com";
        String phone="32526764788";
        String birthday="2023-01-01";
        String password="42gf34g3";
        String picture="image1";
        Toast.makeText(this, "you click", Toast.LENGTH_SHORT).show();

        dbmanager_user.open();
        dbmanager_user.insert(name, email, phone, birthday, password, picture);
        dbmanager_user.close();
/*
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);*/
    }
}