package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_train;

public class mrt_kajang extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_train dbmanager_train;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private String location, from_where, to_where;
    public int pos, from_pos, to_pos;
    private VideoView bg;
    private int currentPosition;
    private Button kajang, stadium_kajang, sungai_jernih, bukit_dokong, batu_11_cheras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mrt_kajang);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        kajang = findViewById(R.id.kajang);
        stadium_kajang = findViewById(R.id.stadium_kajang);
        sungai_jernih = findViewById(R.id.sungai_jernih);
        bukit_dokong = findViewById(R.id.bukit_dokong);
        batu_11_cheras = findViewById(R.id.batu_11_cheras);

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

    //Functions
    public void kajang(View view) {
        location = "Kajang";
        pos=1;

        kajang.setBackgroundResource(R.drawable.select);
        stadium_kajang.setBackgroundResource(R.drawable.unselect);
        sungai_jernih.setBackgroundResource(R.drawable.unselect);
        bukit_dokong.setBackgroundResource(R.drawable.unselect);
        batu_11_cheras.setBackgroundResource(R.drawable.unselect);

        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void stadium_kajang(View view) {
        location = "Stadium Kajang";
        pos=2;

        stadium_kajang.setBackgroundResource(R.drawable.select);
        kajang.setBackgroundResource(R.drawable.unselect);
        sungai_jernih.setBackgroundResource(R.drawable.unselect);
        bukit_dokong.setBackgroundResource(R.drawable.unselect);
        batu_11_cheras.setBackgroundResource(R.drawable.unselect);

        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void sungai_jernih(View view) {
        location = "Sungai Jernih";
        pos=3;

        sungai_jernih.setBackgroundResource(R.drawable.select);
        stadium_kajang.setBackgroundResource(R.drawable.unselect);
        kajang.setBackgroundResource(R.drawable.unselect);
        bukit_dokong.setBackgroundResource(R.drawable.unselect);
        batu_11_cheras.setBackgroundResource(R.drawable.unselect);

        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void bukit_dokong(View view) {
        location = "Bukit Dokong";
        pos=4;

        bukit_dokong.setBackgroundResource(R.drawable.select);
        stadium_kajang.setBackgroundResource(R.drawable.unselect);
        kajang.setBackgroundResource(R.drawable.unselect);
        sungai_jernih.setBackgroundResource(R.drawable.unselect);
        batu_11_cheras.setBackgroundResource(R.drawable.unselect);

        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void batu_11_cheras(View view) {
        location = "Batu 11 Cheras";
        pos=5;

        batu_11_cheras.setBackgroundResource(R.drawable.select);
        stadium_kajang.setBackgroundResource(R.drawable.unselect);
        kajang.setBackgroundResource(R.drawable.unselect);
        sungai_jernih.setBackgroundResource(R.drawable.unselect);
        bukit_dokong.setBackgroundResource(R.drawable.unselect);

        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }

    public void from(View view) {
        if (location==null) {
            Toast.makeText(this, "Please select a location", Toast.LENGTH_SHORT).show();
        }
        else {
            TextView from = findViewById(R.id.from);
            Button fromButton = findViewById(R.id.button_from);
            from.setText(location);
            fromButton.setText("From");
            from_pos=pos;
            from_where=location;
        }
    }

    public void to(View view) {
        if (location==null) {
            Toast.makeText(this, "Please select a location", Toast.LENGTH_SHORT).show();
        }
        else {
            TextView to = findViewById(R.id.to);
            Button toButton = findViewById(R.id.button_to);
            to.setText(location);
            toButton.setText("To");
            to_pos=pos;
            to_where=location;
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

    public void calculate(View view) {
        TextView arrival_time = findViewById(R.id.time);
        TextView total_cost = findViewById(R.id.cost);
        int time;
        double cost;
        if (from_pos > to_pos) {
            time=(from_pos-to_pos)*5;
            cost=0.4+(double) (from_pos-to_pos)*0.4;
            arrival_time.setText(time+"minutes");
            total_cost.setText(String.format("RM%.2f",cost));
            dbmanager_train = new dbmanager_train(this);
            dbmanager_train.open();
            dbmanager_train.insert(getUserID(), "mrt kajang", from_where, to_where, time+"minutes", cost);
            dbmanager_train.close();
            main.updateVersion();
        }
        else if (from_pos < to_pos) {
            time=(to_pos-from_pos)*5;
            cost=0.4+(double) (to_pos-from_pos)*0.4;
            arrival_time.setText(time+"minutes");
            total_cost.setText(String.format("RM%.2f",cost));
            dbmanager_train = new dbmanager_train(this);
            dbmanager_train.open();
            dbmanager_train.insert(getUserID(), "mrt kajang", from_where, to_where, time+"minutes", cost);
            dbmanager_train.close();
            main.updateVersion();
        }
        else if (from_pos == to_pos) Toast.makeText(this, "Invalid location. \nSelect different location.", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Please select a location", Toast.LENGTH_SHORT).show();
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