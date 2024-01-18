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
import com.example.groupassignment.dbmanagers.dbmanager_pax;
import com.example.groupassignment.dbmanagers.dbmanager_user;

public class pax_flight extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_pax dbmanager_pax;
    private com.example.groupassignment.dbmanagers.dbmanager_user dbmanager_user;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private VideoView bg;
    private int currentPosition;
    private com.example.groupassignment.dbmanagers.dbmanager_flight dbmanager_flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pax_flight);

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
        });    }

    public void buy(View view) {
        EditText ticket_amount = findViewById(R.id.editTextNumber2);
        EditText adult_amount = findViewById(R.id.adult_amount);
        EditText kid_amount = findViewById(R.id.kid_amount);

        if (ticket_amount.getText().toString().equals("")||adult_amount.getText().toString().equals("")||kid_amount.getText().toString().equals("")) {
            Toast.makeText(this, "Please fill in all the blanks", Toast.LENGTH_SHORT).show();
        }
        else {
            int amount = Integer.parseInt(ticket_amount.getText().toString());
            int adult = Integer.parseInt(adult_amount.getText().toString());
            int kid = Integer.parseInt(kid_amount.getText().toString());
            int sum = adult+kid;

            if(sum == amount) {
                dbmanager_flight = new dbmanager_flight(this);
                dbmanager_flight.open();
                Cursor cursor = dbmanager_flight.fetch();
                cursor.moveToLast();
                dbmanager_flight.update(Integer.parseInt(cursor.getString(0)), null, null, null, null, -1, amount);
                cursor.close();
                dbmanager_flight.close();
                main.updateVersion();

                Intent intent = new Intent(pax_flight.this, info_flight.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "The sum of adult and kid tickets (" + sum + ") doesn't match the total amount.", Toast.LENGTH_SHORT).show();
            }
        }
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
