package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class edit_profile extends AppCompatActivity {
    private dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(1));
        dbmanager_login_history.close();

        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor_user = dbmanager_user.fetch(user_id);

        EditText name = findViewById(R.id.name_text);
        EditText phone = findViewById(R.id.phone_text);
        EditText email = findViewById(R.id.email_text);
        EditText birthday = findViewById(R.id.birthday_text);

        name.setText(cursor_user.getString(1));
        phone.setText(cursor_user.getString(3));
        email.setText(cursor_user.getString(2));
        birthday.setText(cursor_user.getString(4));

        ImageView picture = findViewById(R.id.imageView);
        picture.setImageResource(getResources().getIdentifier(cursor_user.getString(6) + "_100", "drawable", getPackageName()));
        dbmanager_user.close();
    }


    public void cancel_edit(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void save_edit(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(1));
        dbmanager_login_history.close();

        String name = ((EditText) findViewById(R.id.name_text)).getText().toString();
        String email = ((EditText) findViewById(R.id.email_text)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone_text)).getText().toString();
        String birthday = ((EditText) findViewById(R.id.birthday_text)).getText().toString();

        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        dbmanager_user.update(user_id, name, email, phone, birthday, null, null);
        dbmanager_user.close();

        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void edit_image(View view) {
        Intent intent = new Intent (this, set_picture.class);
        startActivity(intent);
    }
}