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

import com.example.groupassignment.dbmanagers.dbmanager_book_summary;
import com.example.groupassignment.dbmanagers.dbmanager_bus;
import com.example.groupassignment.dbmanagers.dbmanager_choose_bus;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_plan_summary;

public class seat_bus extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_bus dbmanager_bus;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private com.example.groupassignment.dbmanagers.dbmanager_plan_summary dbmanager_plan_summary;
    private com.example.groupassignment.dbmanagers.dbmanager_book_summary dbmanager_book_summary;
    private com.example.groupassignment.dbmanagers.dbmanager_choose_bus dbmanager_choose_bus;
 private VideoView bg;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_bus);

        //Top Navigation
        BaseActivity.setupToolbar(this);
//background.video(this);
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

    public void pick_up(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        String activity = cursor_login.getString(2);
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();

        Integer seat = Integer.valueOf(((EditText) findViewById(R.id.inputfrom5)).getText().toString());
        dbmanager_bus = new dbmanager_bus(this);
        dbmanager_bus.open();
        Cursor cursor_bus = dbmanager_bus.fetch(getBusID());
        if (Integer.parseInt(cursor_bus.getString(0))<seat) {
            Toast.makeText(this, "Not Enough Seat", Toast.LENGTH_SHORT).show();
        }
        else {
            dbmanager_bus.update(getBusID(), Integer.parseInt(cursor_bus.getString(0)) - seat, null);
        }
        dbmanager_bus.close();

        if (activity.equals("plan")) {
            dbmanager_plan_summary = new dbmanager_plan_summary(this);
            dbmanager_plan_summary.open();
            dbmanager_plan_summary.insert("bus", getBusID(), getUserID(), 1); //change login id later
            dbmanager_plan_summary.close();
            main.updateVersion();
            Intent intent = new Intent(this,planning_summary.class);
            startActivity(intent);
        }
        else if (activity.equals("book")) {
            dbmanager_book_summary = new dbmanager_book_summary(this);
            dbmanager_book_summary.open();
            dbmanager_book_summary.insert("bus", getBusID(), getUserID(), getLoginID());
            dbmanager_book_summary.close();
            main.updateVersion();
            Intent intent = new Intent(this,booking_summary.class);
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

    public int getBusID(){
        dbmanager_choose_bus = new dbmanager_choose_bus(this);
        dbmanager_choose_bus.open();
        Cursor cursor_choose = dbmanager_choose_bus.fetch();
        cursor_choose.moveToLast();
        int bus_id=Integer.parseInt(cursor_choose.getString(1));
        dbmanager_choose_bus.close();
        main.updateVersion();
        return bus_id;
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