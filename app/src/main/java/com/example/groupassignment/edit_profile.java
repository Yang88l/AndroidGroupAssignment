package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_user;

public class edit_profile extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);

        EditText name = findViewById(R.id.name_text);
        EditText phone = findViewById(R.id.phone_text);
        EditText email = findViewById(R.id.email_text);
        EditText birthday = findViewById(R.id.birthday_text);

        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor_user = dbmanager_user.fetch(getUserID());

        name.setText(cursor_user.getString(1));
        phone.setText(cursor_user.getString(3));
        email.setText(cursor_user.getString(2));
        birthday.setText(cursor_user.getString(4));

        ImageView picture = findViewById(R.id.imageView);
        picture.setImageResource(getResources().getIdentifier(cursor_user.getString(6) + "_100", "drawable", getPackageName()));

        cursor_user.close();
        dbmanager_user.close();
        main.updateVersion();
    }


    public void cancel_edit(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void save_edit(View view) {
        String name = ((EditText) findViewById(R.id.name_text)).getText().toString();
        String email = ((EditText) findViewById(R.id.email_text)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone_text)).getText().toString();
        String birthday = ((EditText) findViewById(R.id.birthday_text)).getText().toString();

        Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();

        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        dbmanager_user.update(getUserID(), name, email, phone, birthday, null, null);
        dbmanager_user.close();
        main.updateVersion();

        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void edit_image(View view) {
        Intent intent = new Intent (this, set_picture.class);
        startActivity(intent);
    }

    public int getUserID(){
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        int user_id=Integer.parseInt(cursor_login.getString(1));
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();
        return user_id;
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