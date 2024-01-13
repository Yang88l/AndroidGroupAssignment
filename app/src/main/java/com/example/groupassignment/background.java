package com.example.groupassignment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.VideoView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class background extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background);
    }

    public static void video(AppCompatActivity activity) {
        VideoView background = activity.findViewById(R.id.background);

        String videoPath = "android.resource://" + activity.getPackageName() + "/" + R.raw.background;
        Uri videoUri = Uri.parse(videoPath);
        background.setVideoURI(videoUri);

        // Start playing the video in a loop
        background.start();
        background.setOnPreparedListener(mp -> mp.setLooping(true));
        }

}


