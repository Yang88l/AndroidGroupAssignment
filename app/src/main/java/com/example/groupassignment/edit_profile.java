package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class edit_profile extends AppCompatActivity {
    private dbmanager_user dbmanager_user;
    private dbhelper_user dbhelper_user;
    private TextView name_text, email_text, phone_text, birthday_text;
    private String name, email, phone, birthday, password, picture;
    public int user_id=1; // Replace with the actual user ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        dbmanager_user = new dbmanager_user(this);
        dbhelper_user = new dbhelper_user(this);

        name_text = findViewById(R.id.name_text);
        email_text = findViewById(R.id.email_text);
        phone_text = findViewById(R.id.phone_text);
        birthday_text = findViewById(R.id.birthday_text);

        //Specify Specific Data you want to get
        dbmanager_user.open();

        String[] columnsToSelect = new String[]{
                dbhelper_user.NAME,
                dbhelper_user.EMAIL,
                dbhelper_user.PHONE,
                dbhelper_user.BIRTHDAY,
                dbhelper_user.PASSWORD,
                dbhelper_user.PICTURE
        };

        //Retrieve the data
        Cursor cursor = dbmanager_user.fetch(columnsToSelect, user_id);

        // Get column indices
        int nameIndex = cursor.getColumnIndex(dbhelper_user.NAME);
        int emailIndex = cursor.getColumnIndex(dbhelper_user.EMAIL);
        int phoneIndex = cursor.getColumnIndex(dbhelper_user.PHONE);
        int birthdayIndex = cursor.getColumnIndex(dbhelper_user.BIRTHDAY);
        int passwordIndex = cursor.getColumnIndex(dbhelper_user.PASSWORD);
        int pictureIndex = cursor.getColumnIndex(dbhelper_user.PICTURE);

        // Check if column indices are valid
        if (nameIndex >= 0 && emailIndex >= 0 && phoneIndex >= 0 && birthdayIndex >= 0 && passwordIndex >= 0 && pictureIndex >= 0 ) {
            // Extract values from the Cursor
            name = cursor.getString(nameIndex);
            email = cursor.getString(emailIndex);
            phone = cursor.getString(phoneIndex);
            birthday = cursor.getString(birthdayIndex);;
            password = cursor.getString(passwordIndex);;
            picture = cursor.getString(pictureIndex);;
        }
        //Display the data
        name_text.setText(name);
        email_text.setText(email);
        phone_text.setText(phone);
        birthday_text.setText(birthday);


        cursor.close();
        dbmanager_user.close();
    }


    public void cancel_edit(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void save_edit(View view) {
        name = name_text.getText().toString();
        email = email_text.getText().toString();
        phone = phone_text.getText().toString();
        birthday = birthday_text.getText().toString();

        dbmanager_user.open();
        dbmanager_user.update(user_id, name, email, phone, birthday, password, picture);
        dbmanager_user.close();

        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void edit_image(View view) {
        Intent intent = new Intent (this, set_picture.class);
        startActivity(intent);
    }
}