package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class log_in extends AppCompatActivity {
    private dbmanager_user dbmanager_user;
    private dbhelper_user dbhelper_user;
    private EditText input_password_text,input_name_text;
    private String name, email, phone, birthday, password, picture, input_name;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        dbmanager_user = new dbmanager_user(this);
        dbhelper_user = new dbhelper_user(this);

        input_password_text = findViewById(R.id.input_password);
        input_name_text = findViewById(R.id.input_username);



        //Specify Specific Data you want to get
        dbmanager_user.open();

        String[] columnsToSelect = new String[]{
                dbhelper_user.USER_ID,
                dbhelper_user.NAME,
                dbhelper_user.EMAIL,
                dbhelper_user.PHONE,
                dbhelper_user.BIRTHDAY,
                dbhelper_user.PASSWORD,
                dbhelper_user.PICTURE
        };

        //Retrieve the data
        Cursor cursor = dbmanager_user.fetchALL(columnsToSelect, input_name, password);

        // Get column indices
        int nameIndex = cursor.getColumnIndex(dbhelper_user.NAME);
        int emailIndex = cursor.getColumnIndex(dbhelper_user.EMAIL);
        int phoneIndex = cursor.getColumnIndex(dbhelper_user.PHONE);
        int birthdayIndex = cursor.getColumnIndex(dbhelper_user.BIRTHDAY);
        int user_idIndex = cursor.getColumnIndex(dbhelper_user.USER_ID);
        int passwordIndex = cursor.getColumnIndex(dbhelper_user.PASSWORD);
        int pictureIndex = cursor.getColumnIndex(dbhelper_user.PICTURE);

        // Check if column indices are valid
        if (nameIndex >= 0 && emailIndex >= 0 && phoneIndex >= 0 && birthdayIndex >= 0 && user_idIndex>= 0 && passwordIndex>= 0 && pictureIndex>= 0 ) {
            // Extract values from the Cursor
            name = cursor.getString(nameIndex);
            email = cursor.getString(emailIndex);
            phone = cursor.getString(phoneIndex);
            birthday = cursor.getString(birthdayIndex);;
            user_id = cursor.getInt(user_idIndex);;
            password = cursor.getString(passwordIndex);;
            picture = cursor.getString(pictureIndex);;
        }

        cursor.close();
        dbmanager_user.close();
    }

    public void log_in(View view) {
        String input_name = input_name_text.getText().toString();
        String password = input_password_text.getText().toString();

        if (true) {
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Unable to Login!", Toast.LENGTH_SHORT).show();
        }
    }

    public void sign_up_here(View view) {
        Intent intent = new Intent (this, sign_up.class);
        startActivity(intent);
    }
}