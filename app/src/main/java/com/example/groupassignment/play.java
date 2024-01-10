package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class play extends AppCompatActivity {
    private com.example.groupassignment.dbmanager_favourite dbmanager_favourite;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
    }
    public void add(View view) {
    }

    public void detail(View view) {
        Intent intent = new Intent(this, information.class);
        startActivity(intent);
    }

    public void sunway(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 1);
        startActivity(intent);
    }

    public void genting(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 2);
        startActivity(intent);
    }

    public void sky(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 3);
        startActivity(intent);
    }

    public void zoo(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 4);
        startActivity(intent);
    }
    public void sunway_favourite(View view) {
        ImageView picture = findViewById(R.id.imageButton12);
        picture.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        dbmanager_favourite.insert(getUserID(), "hotel", 1);
        dbmanager_favourite.close();
        main.updateVersion();
    }

    public void genting_favourite(View view) {
        ImageView picture = findViewById(R.id.imageButton22);
        picture.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        dbmanager_favourite.insert(getUserID(), "hotel", 2);
        dbmanager_favourite.close();
        main.updateVersion();
    }

    public void sky_favourite(View view) {
        ImageView picture = findViewById(R.id.imageButton7);
        picture.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        dbmanager_favourite.insert(getUserID(), "hotel", 3);
        dbmanager_favourite.close();
        main.updateVersion();
    }
    public void zoo_favourite(View view) {
        ImageView picture = findViewById(R.id.imageButton7);
        picture.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        dbmanager_favourite.insert(getUserID(), "hotel", 3);
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