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

public class calendar_flight extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_flight dbmanager_flight;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
 private VideoView bg;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_flight);

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

    public void confirm(View view) {
        EditText date = findViewById(R.id.date);

        if (checkDate(date.getText().toString())) {
            dbmanager_flight = new dbmanager_flight(this);
            dbmanager_flight.open();
            Cursor cursor = dbmanager_flight.fetch();
            cursor.moveToLast();
            dbmanager_flight.update(Integer.parseInt(cursor.getString(0)), null, null, date.getText().toString(), null, -1, -1);
            dbmanager_flight.close();
            main.updateVersion();
            Intent intent = new Intent(calendar_flight.this, time_flight.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Invalid date format", Toast.LENGTH_SHORT).show();
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

    public boolean checkDate(String input) {
        // DD-MM-YYYY
        boolean valid = true;
        int[] i = {0, 1, 3, 4, 6, 7, 8, 9};
        for (int j = 0; j < 8; j++) {
            if (input.length() == 10 && input.charAt(2) == '-' && input.charAt(5) == '-' && Character.isDigit(input.charAt(i[j]))) {
                int day = (input.charAt(0) - '0') * 10 + (input.charAt(1) - '0');
                int month = (input.charAt(3) - '0') * 10 + (input.charAt(4) - '0');
                int year = (input.charAt(6) - '0') * 1000 + (input.charAt(7) - '0') * 100 + (input.charAt(8) - '0') * 10 + (input.charAt(9) - '0');

                if (day < 1 || day > 31) valid = false;
                if (month < 1 || month > 12) valid = false;
                if (year < 2024 || year > 2025) valid = false;
                if (day < 1 || day > daysInMonth(month, year)) valid = false;
            }
            else valid = false;
        }
        return valid;
    }

    public int daysInMonth(int month, int year) {
        switch (month) {
            case 1: // January
            case 3: // March
            case 5: // May
            case 7: // July
            case 8: // August
            case 10: // October
            case 12: // December
                return 31;
            case 4: // April
            case 6: // June
            case 9: // September
            case 11: // November
                return 30;
            case 2: // February
                if (checkLeapYear(year))
                    return 29;
                else
                    return 28;
            default:
                return 0; // Invalid month
        }
    }

    // Helper method to check if a year is a leap year
    public boolean checkLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}