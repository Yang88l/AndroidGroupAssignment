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

import com.example.groupassignment.dbmanagers.dbmanager_flight;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;

public class time_flight extends AppCompatActivity {

    private com.example.groupassignment.dbmanagers.dbmanager_flight dbmanager_flight;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
   private VideoView bg;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_flight);

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

    public void next(View view) {
        EditText time = findViewById(R.id.time);

        if (checkTime(time.getText().toString())) {
            dbmanager_flight = new dbmanager_flight(this);
            dbmanager_flight.open();
            Cursor cursor = dbmanager_flight.fetch();
            cursor.moveToLast();
            dbmanager_flight.update(Integer.parseInt(cursor.getString(0)), time.getText().toString(), null,null, null,-1, -1);
            cursor.close();
            dbmanager_flight.close();
            main.updateVersion();
            Intent intent = new Intent(this, pax_flight.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Invalid time format", Toast.LENGTH_SHORT).show();
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
    public boolean checkTime(String input) {
        // hh:mm:ss
        // 00:00:00 - 23:59:59
        boolean valid = true;
        int[] i = {0, 1, 3, 4, 6, 7};
        for (int j = 0; j < 6; j++) {
            if (input.length() == 8 && input.charAt(2) == ':' && input.charAt(5) == ':' && Character.isDigit(input.charAt(i[j]))) {
                int hour = (input.charAt(0) - '0') * 10 + (input.charAt(1) - '0');
                if (hour < 0 || hour > 23)
                    valid = false;
                int min = (input.charAt(3) - '0') * 10 + (input.charAt(4) - '0');
                if (min < 0 || min > 59)
                    valid = false;
                int sec = (input.charAt(6) - '0') * 10 + (input.charAt(7) - '0');
                if (sec < 0 || sec > 59)
                    valid = false;
            } else {
                valid = false;
            }
        }
        return valid;
    }

}