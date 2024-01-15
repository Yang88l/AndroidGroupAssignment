package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class select_location extends AppCompatActivity implements View.OnClickListener {
    private static final int BUTTON_TAG_FIRST = 0;
    private static final int BUTTON_TAG_SECOND = 1;
 private VideoView bg;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_location);

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

        ConstraintLayout parentLayout = findViewById(R.id.parent_layout);

        for (int i = 0; i < parentLayout.getChildCount(); i++) {
            View child = parentLayout.getChildAt(i);
            if (child instanceof Button) {
                int buttonId = child.getId();
                System.out.println("Button at position " + i + " has ID: " + getResources().getResourceEntryName(buttonId));
                if (i == 6) {
                    child.setTag(BUTTON_TAG_SECOND);
                } else {
                    child.setTag(BUTTON_TAG_FIRST);
                }
                child.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int buttonTag = (int) v.getTag();

        if (buttonTag == BUTTON_TAG_FIRST) {
            startActivity(new Intent(this, choose.class));
        } else {
            startActivity(new Intent(this, main.class));
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