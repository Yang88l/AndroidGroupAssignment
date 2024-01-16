package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.groupassignment.dbmanagers.dbmanager_book_summary;
import com.example.groupassignment.dbmanagers.dbmanager_choose_airline;
import com.example.groupassignment.dbmanagers.dbmanager_flight;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_plan_summary;

public class info_flight extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_flight dbmanager_flight;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private com.example.groupassignment.dbmanagers.dbmanager_book_summary dbmanager_book_summary;
    private com.example.groupassignment.dbmanagers.dbmanager_plan_summary dbmanager_plan_summary;
    private com.example.groupassignment.dbmanagers.dbmanager_choose_airline dbmanager_choose_airline;
     private VideoView bg;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_flight);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
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
        //get date
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        Cursor cursor = dbmanager_flight.fetch();
        cursor.moveToLast();
        String time = cursor.getString(1);
        String name = cursor.getString(2);
        String date = cursor.getString(3);
        String state = cursor.getString(4);
        String pax = cursor.getString(6);

        Toast.makeText(this, date+"", Toast.LENGTH_SHORT).show();

        cursor.close();
        dbmanager_flight.close();
        main.updateVersion();

        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        String activity = cursor_login.getString(2);
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();

        TextView text = findViewById(R.id.flight_information_text);
        if (activity.equals("plan")) {
            text.setText("You have made a flight planning from " + state
                    + " on " + date + " at " + time + "."
                    + "\nYour airline is " + name + "."
                    + "\nThe price is RM" + (Integer.parseInt(pax) * 99) + " for " + pax + " person."
            );
        }
        else if (activity.equals("book")) {
            text.setText("You have made a flight booking from " + state
                    + "\non " + date + " at " + time + "."
                    + "\nYour airline is " + name + "."
                    + "\nThe price is RM" + (Integer.parseInt(pax) * 99) + " for " + pax + " person."
            );
        }
    }

    public void finish(View view) {
        //get activity
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        String activity = cursor_login.getString(2);
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();

        if (activity.equals("plan")) {
            dbmanager_plan_summary = new dbmanager_plan_summary(this);
            dbmanager_plan_summary.open();
            dbmanager_plan_summary.insert("flight", getFlightID(), getUserID(), getLoginID());
            dbmanager_plan_summary.close();
            main.updateVersion();
            Intent intent = new Intent(this,planning_summary.class);
            startActivity(intent);
        }
        else if (activity.equals("book")) {
            dbmanager_book_summary = new dbmanager_book_summary(this);
            dbmanager_book_summary.open();
            dbmanager_book_summary.insert("flight", getFlightID(), getUserID(), getLoginID());
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

    public int getFlightID(){
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        Cursor cursor = dbmanager_flight.fetch();
        int id = Integer.parseInt(cursor.getString(0));
        cursor.close();
        dbmanager_flight.close();
        main.updateVersion();
        return id;
    }

    public int getAirlineID(){
        dbmanager_choose_airline = new dbmanager_choose_airline(this);
        dbmanager_choose_airline.open();
        Cursor cursor_airline = dbmanager_choose_airline.fetch();
        cursor_airline.moveToLast();
        int airline_id=Integer.parseInt(cursor_airline.getString(1));
        cursor_airline.close();
        dbmanager_choose_airline.close();
        main.updateVersion();
        return airline_id;
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
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
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