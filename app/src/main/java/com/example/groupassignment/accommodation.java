package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class accommodation extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_favourite dbmanager_favourite;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accommodation);
    }

    public void like(View view) {
        //like button
    }

    public void bovelord_hotel(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "hotel");
        intent.putExtra("_id", 1);
        startActivity(intent);
    }

    public void crystal_hotel(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "hotel");
        intent.putExtra("_id", 2);
        startActivity(intent);
    }

    public void ocean_hotel(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "hotel");
        intent.putExtra("_id", 3);
        startActivity(intent);
    }

    public void bevelord_favourite(View view) {
        ImageView picture = findViewById(R.id.imageButton12);
        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        Cursor cursor = dbmanager_favourite.fetch(getUserID());
        if (cursor.getString(4).equals("0")) {
            picture.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
            dbmanager_favourite.insert(getUserID(), "hotel", 1, 1);
        }
        else if (cursor.getString(4).equals("1")) {
            picture.setImageResource(getResources().getIdentifier(("love"), "drawable", getPackageName()));
            dbmanager_favourite.insert(getUserID(), "hotel", 1, 0);
        }
        dbmanager_favourite.close();
        main.updateVersion();
    }

    public void crystal_favourite(View view) {
        ImageView picture = findViewById(R.id.imageButton22);
        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        Cursor cursor = dbmanager_favourite.fetch(getUserID());
        if (cursor.getString(4).equals("0")) {
            picture.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
            dbmanager_favourite.insert(getUserID(), "hotel", 2, 1);
        }
        else if (cursor.getString(4).equals("1")) {
            picture.setImageResource(getResources().getIdentifier(("love"), "drawable", getPackageName()));
            dbmanager_favourite.insert(getUserID(), "hotel", 2, 0);
        }
        dbmanager_favourite.close();
        main.updateVersion();
    }

    public void ocean_favourite(View view) {
        ImageView picture = findViewById(R.id.imageButton7);
        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        Cursor cursor = dbmanager_favourite.fetch(getUserID());
        if (cursor.getString(4).equals("0")) {
            picture.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
            dbmanager_favourite.insert(getUserID(), "hotel", 3, 1);
        }
        else if (cursor.getString(4).equals("1")) {
            picture.setImageResource(getResources().getIdentifier(("love"), "drawable", getPackageName()));
            dbmanager_favourite.insert(getUserID(), "hotel", 3, 0);
        }
        dbmanager_favourite.close();
        main.updateVersion();
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