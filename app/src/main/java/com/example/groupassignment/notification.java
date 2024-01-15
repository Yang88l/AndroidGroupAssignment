package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.groupassignment.dbmanagers.dbmanager_login_history;

import java.util.Random;

public class notification extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
     private VideoView bg;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

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


        TextView text1 = findViewById(R.id.text_1);
        TextView text2 = findViewById(R.id.text_2);
        TextView text3 = findViewById(R.id.text_3);
        TextView text4 = findViewById(R.id.text_4);

        //Randomize notification
        Random random = new Random();
        //Delay random message
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int random_notif = random.nextInt(10);

                switch(random_notif) {
                    case 1: text1.setText("Enjoy our app? Go to BookSwift.com to give us a feedback!");
                        break;
                    case 2: text1.setText("Any issue or concern about our app? Go to BookSwift.com, Customer Support");
                        break;
                    case 3: text1.setText("Zoo Negara is trending now. Get it before it sold out!");
                        break;
                    case 4: text1.setText("Genting Highland is trending now. Get it before it sold out!");
                        break;
                    case 5: text1.setText("Sky Mirror is trending now. Get it before it sold out!");
                        break;
                    case 6: text1.setText("Sunway Lagoon is trending now. Get it before it sold out!");
                        break;
                    case 7: //do nothing
                        break;
                    case 8: //do nothing
                        break;
                    case 9: //do nothing
                        break;
                    case 10: //do nothing
                        break;
                }
            }
        }, 4000);
    }
    public void notification(View view) {
        Toast.makeText(this, "You are already inside!", Toast.LENGTH_SHORT).show();
    }
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
    public void message(View view) {
        Intent intent = new Intent(this,message.class);
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