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

    private dbmanager_user dbmanager_user;
    private dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor_user = dbmanager_user.fetch(getUserID());

        TextView title = findViewById(R.id.textView);
        TextView name = findViewById(R.id.textView3);
        TextView phone = findViewById(R.id.textView7);
        TextView email = findViewById(R.id.textView16);
        TextView birthday = findViewById(R.id.textView17);

        title.setText("Hi, "+cursor_user.getString(1));
        name.setText(cursor_user.getString(1));
        email.setText(cursor_user.getString(2));
        phone.setText(cursor_user.getString(3));
        birthday.setText(cursor_user.getString(4));


        ImageView picture = findViewById(R.id.imageView);
        picture.setImageResource(getResources().getIdentifier((cursor_user.getString(6) + "_100"), "drawable", getPackageName()));
        cursor_user.close();

        dbmanager_user.close();
        main.updateVersion();
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
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        dbmanager_login_history.insert(getUserID(), "logged out", "null");
        dbmanager_login_history.close();
        main.updateVersion();
        Intent intent = new Intent (this, main.class);
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
}