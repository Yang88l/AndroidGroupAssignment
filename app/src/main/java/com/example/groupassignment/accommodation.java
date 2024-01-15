package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.groupassignment.dbmanagers.dbmanager_favourite;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;

public class accommodation extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_favourite dbmanager_favourite;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accommodation);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);

        if (login_status()) {
            ImageView picture1 = findViewById(R.id.imageButton12);
            dbmanager_favourite = new dbmanager_favourite(this);
            dbmanager_favourite.open();
            Cursor cursor1 = dbmanager_favourite.fetch(getUserID(), "hotel", 1);
            if (cursor1.getString(4).equals("1")) {
                picture1.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
            }
            else if (cursor1.getString(4).equals("0")) {
                picture1.setImageResource(getResources().getIdentifier(("love"), "drawable", getPackageName()));
            }
            dbmanager_favourite.close();
            main.updateVersion();

            ImageView picture2 = findViewById(R.id.imageButton22);
            dbmanager_favourite = new dbmanager_favourite(this);
            dbmanager_favourite.open();
            Cursor cursor2 = dbmanager_favourite.fetch(getUserID(), "hotel", 2);
            if (cursor2.getString(4).equals("1")) {
                picture2.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
            }
            else if (cursor2.getString(4).equals("0")) {
                picture2.setImageResource(getResources().getIdentifier(("love"), "drawable", getPackageName()));
            }
            dbmanager_favourite.close();
            main.updateVersion();

            ImageView picture3 = findViewById(R.id.imageButton7);
            dbmanager_favourite = new dbmanager_favourite(this);
            dbmanager_favourite.open();
            Cursor cursor3 = dbmanager_favourite.fetch(getUserID(), "hotel", 3);
            if (cursor3.getString(4).equals("1")) {
                picture3.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
            }
            else if (cursor3.getString(4).equals("0")) {
                picture3.setImageResource(getResources().getIdentifier(("love"), "drawable", getPackageName()));
            }
            dbmanager_favourite.close();
            main.updateVersion();
        }
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
        if (login_status()) {
            ImageView picture = findViewById(R.id.imageButton12);
            dbmanager_favourite = new dbmanager_favourite(this);
            dbmanager_favourite.open();
            Cursor cursor = dbmanager_favourite.fetch(getUserID(), "hotel", 1);
            if (cursor.getString(4).equals("0")) {
                picture.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
                dbmanager_favourite.update(getUserID(), "hotel", 1, 1);
            }
            else if (cursor.getString(4).equals("1")) {
                picture.setImageResource(getResources().getIdentifier(("love"), "drawable", getPackageName()));
                dbmanager_favourite.update(getUserID(), "hotel", 1, 0);
            }
            dbmanager_favourite.close();
            main.updateVersion();
        }
    }

    public void crystal_favourite(View view) {
        if (login_status()) {
            ImageView picture = findViewById(R.id.imageButton22);
            dbmanager_favourite = new dbmanager_favourite(this);
            dbmanager_favourite.open();
            Cursor cursor = dbmanager_favourite.fetch(getUserID(), "hotel", 2);
            if (cursor.getString(4).equals("0")) {
                picture.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
                dbmanager_favourite.update(getUserID(), "hotel", 2, 1);
            } else if (cursor.getString(4).equals("1")) {
                picture.setImageResource(getResources().getIdentifier(("love"), "drawable", getPackageName()));
                dbmanager_favourite.update(getUserID(), "hotel", 2, 0);
            }
            dbmanager_favourite.close();
            main.updateVersion();
        }
    }

    public void ocean_favourite(View view) {
        if (login_status()) {
            ImageView picture = findViewById(R.id.imageButton7);
            dbmanager_favourite = new dbmanager_favourite(this);
            dbmanager_favourite.open();
            Cursor cursor = dbmanager_favourite.fetch(getUserID(), "hotel", 3);
            if (cursor.getString(4).equals("0")) {
                picture.setImageResource(getResources().getIdentifier(("love_red"), "drawable", getPackageName()));
                dbmanager_favourite.update(getUserID(), "hotel", 3, 1);
            } else if (cursor.getString(4).equals("1")) {
                picture.setImageResource(getResources().getIdentifier(("love"), "drawable", getPackageName()));
                dbmanager_favourite.update(getUserID(), "hotel", 3, 0);
            }
            dbmanager_favourite.close();
            main.updateVersion();
        }
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

    public boolean login_status(){
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        String status=cursor.getString(3);
        cursor.close();
        dbmanager_login_history.close();
        main.updateVersion();

        if (status.equals("logged in")) {
            return true;
        }
        else {
            Toast.makeText(this, "You are not logged in.", Toast.LENGTH_SHORT).show();
            return false;
        }
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
        startActivity(new Intent(this, profile.class));
    }
}