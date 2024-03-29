package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.groupassignment.dbmanagers.dbmanager_choose_airline;
import com.example.groupassignment.dbmanagers.dbmanager_flight;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;

public class airline extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_choose_airline dbmanager_choose_airline;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
   private VideoView bg;
    private int currentPosition;
    private com.example.groupassignment.dbmanagers.dbmanager_flight dbmanager_flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airline);

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
    }

    public void airasia(View view) {
        insertChooseAirline(1);
    }
    public void firefly(View view) {
        insertChooseAirline(2);
    }
    public void berjaya_air(View view) {
        insertChooseAirline(3);
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

    public void insertChooseAirline(int airline_id){
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        Cursor cursor = dbmanager_flight.fetch();
        cursor.moveToLast();
        switch (airline_id){
            case 1: dbmanager_flight.update(Integer.parseInt(cursor.getString(0)),null,"Air Asia", null,null,-1, -1);
            case 2: dbmanager_flight.update(Integer.parseInt(cursor.getString(0)),null,"Fire Fly", null,null,-1, -1);
            case 3: dbmanager_flight.update(Integer.parseInt(cursor.getString(0)),null,"Berjaya Air", null,null,-1, -1);
        }
        dbmanager_flight.close();
        main.updateVersion();

        Intent intent = new Intent(this, calendar_flight.class);
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