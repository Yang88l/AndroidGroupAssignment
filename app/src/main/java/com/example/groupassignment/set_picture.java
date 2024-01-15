package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.example.groupassignment.dbmanagers.dbmanager_user;

public class set_picture extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_user dbmanager_user;
      private VideoView bg;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_picture);

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

    public void camera(View view) {
        //open camera
    }

    public void profile1(View view) {
        setProfile("profile1");
    }

    public void profile2(View view) {
        setProfile("profile2");
    }

    public void profile3(View view) {
        setProfile("profile3");
    }

    public void profile4(View view) {
        setProfile("profile4");
    }

    public void profile5(View view) {
        setProfile("profile5");
    }

    public void setProfile(String picture){
        dbmanager_user = new dbmanager_user(this);
        dbmanager_user.open();
        Cursor cursor = dbmanager_user.fetchALL();
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(0));
        cursor.close();
        dbmanager_user.update(user_id, null, null, null, null, null, picture);
        dbmanager_user.close();
        main.updateVersion();
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
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