package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_plan_history;
import com.example.groupassignment.dbmanagers.dbmanager_book_history;
import com.example.groupassignment.dbmanagers.dbmanager_user;

public class log_in extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private com.example.groupassignment.dbmanagers.dbmanager_plan_history dbmanager_plan_history;
    private com.example.groupassignment.dbmanagers.dbmanager_book_history dbmanager_book_history;
 private VideoView bg;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        bg = findViewById(R.id.background);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.background;
        Uri videoUri = Uri.parse(videoPath);
        bg.setVideoURI(videoUri);

        bg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                bg.start();
            }
        });

    }


    public void log_in(View view) {
        int user_id;
        String passwd;

        String name = ((EditText) findViewById(R.id.input_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.input_password)).getText().toString();

        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetchByName(name);
        if (name.equals("")||password.equals("")) {
            Toast.makeText(this, "Please fill in all the blanks", Toast.LENGTH_SHORT).show();
            cursor.close();
            dbmanager_user.close();
            main.updateVersion();
        }
        else if (cursor.getCount() == 0) {
            Toast.makeText(this, "Name not registered.", Toast.LENGTH_SHORT).show();
            cursor.close();
            dbmanager_user.close();
            main.updateVersion();
        }
        else {
            user_id=Integer.parseInt(cursor.getString(0));
            passwd=cursor.getString(5);
            cursor.close();
            dbmanager_user.close();
            main.updateVersion();
            if (password.equals(passwd)) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                dbmanager_login_history = new dbmanager_login_history(this);
                dbmanager_login_history.open();
                dbmanager_login_history.insert(user_id, "logged in", "null");
                dbmanager_login_history.close();
                main.updateVersion();

                dbmanager_plan_history = new dbmanager_plan_history(this);
                dbmanager_plan_history.open();
                dbmanager_plan_history.insert(getUserID(), getLoginID(), "", "");
                dbmanager_plan_history.close();
                main.updateVersion();

                dbmanager_book_history = new dbmanager_book_history(this);
                dbmanager_book_history.open();
                dbmanager_book_history.insert(getUserID(), getLoginID(), "", "");
                dbmanager_book_history.close();
                main.updateVersion();

                Intent intent = new Intent(this, profile.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show();
            }
        }




    }

    public void sign_up_here(View view) {
        Intent intent = new Intent (this, sign_up.class);
        startActivity(intent);
    }

    public void notification(View view) { startActivity(new Intent(this, notification.class));}
    public void home(View view) {
        startActivity(new Intent(this, main.class));
    }

    public void heart(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        String status=cursor.getString(3);
        cursor.close();
        dbmanager_login_history.close();
        main.updateVersion();
        if (status.equals("logged out")) {
            Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show();
        }
        else if (status.equals("logged in")) {
            Intent intent = new Intent(this, my_favourite.class);
            startActivity(intent);
        }
    }

    public void history(View view) {
        startActivity(new Intent(this, plan_history.class));
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
        if (status.equals("logged out")) {
            Intent intent = new Intent(this, log_in.class);
            startActivity(intent);
        }
        else if (status.equals("logged in")) {
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
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

    public int getLoginID(){
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        int login_id=Integer.parseInt(cursor_login.getString(0));
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();
        return login_id;
    }

    @Override
    protected void onResume() {
        // Resume the video playback from the saved position
        bg.seekTo(currentPosition);
        bg.start();
        super.onResume();
    }
    @Override
    protected void onRestart() {
        bg.start();
        super.onRestart();
    }
    @Override
    protected void onPause() {
        // Save the current playback position
        currentPosition = bg.getCurrentPosition();
        // Pause the video playback
        bg.pause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        bg.stopPlayback();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        currentPosition = bg.getCurrentPosition();
        bg.pause();
        super.onBackPressed();
    }
}