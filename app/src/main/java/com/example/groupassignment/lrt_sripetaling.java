package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

public class lrt_sripetaling extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_train dbmanager_train;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private String location, from_where, to_where;
    public int pos, from_pos, to_pos;
    private VideoView bg;
    private int currentPosition;
    private Button sri_petaling, bukit_jalil ,sungai_besi ,tasik_selatan ,bandar_tun_razak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lrt_sripetaling);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        sri_petaling = findViewById(R.id.sri_petaling);
        bukit_jalil = findViewById(R.id.bukit_jalil);
        sungai_besi = findViewById(R.id.sungai_besi);
        tasik_selatan = findViewById(R.id.tasik_selatan);
        bandar_tun_razak = findViewById(R.id.bandar_tun_razak);

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
    public void sri_petaling(View view) {
        location = "Sri Petaling";
        pos=1;

        sri_petaling.setBackgroundResource(R.drawable.select_2);
        bukit_jalil.setBackgroundResource(R.drawable.unselect_2);
        sungai_besi.setBackgroundResource(R.drawable.unselect_2);
        tasik_selatan.setBackgroundResource(R.drawable.unselect_2);
        bandar_tun_razak.setBackgroundResource(R.drawable.unselect_2);

        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void bukit_jalil(View view) {
        location = "Bukit Jalil";
        pos=2;

        bukit_jalil.setBackgroundResource(R.drawable.select_2);
        sri_petaling.setBackgroundResource(R.drawable.unselect_2);
        sungai_besi.setBackgroundResource(R.drawable.unselect_2);
        tasik_selatan.setBackgroundResource(R.drawable.unselect_2);
        bandar_tun_razak.setBackgroundResource(R.drawable.unselect_2);

        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void sungai_besi(View view) {
        location = "Sungai Besi";
        pos=3;

        sungai_besi.setBackgroundResource(R.drawable.select_2);
        sri_petaling.setBackgroundResource(R.drawable.unselect_2);
        bukit_jalil.setBackgroundResource(R.drawable.unselect_2);
        tasik_selatan.setBackgroundResource(R.drawable.unselect_2);
        bandar_tun_razak.setBackgroundResource(R.drawable.unselect_2);

        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void tasik_selatan(View view) {
        location = "Tasik Selatan";
        pos=4;

        tasik_selatan.setBackgroundResource(R.drawable.select_2);
        sri_petaling.setBackgroundResource(R.drawable.unselect_2);
        bukit_jalil.setBackgroundResource(R.drawable.unselect_2);
        sungai_besi.setBackgroundResource(R.drawable.unselect_2);
        bandar_tun_razak.setBackgroundResource(R.drawable.unselect_2);

        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void bandar_tun_razak(View view) {
        location = "Bandar Tun Razak";
        pos=5;

        bandar_tun_razak.setBackgroundResource(R.drawable.select_2);
        sri_petaling.setBackgroundResource(R.drawable.unselect_2);
        bukit_jalil.setBackgroundResource(R.drawable.unselect_2);
        sungai_besi.setBackgroundResource(R.drawable.unselect_2);
        tasik_selatan.setBackgroundResource(R.drawable.unselect_2);

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
            dbmanager_train.insert(getUserID(), "lrt sripetaling", from_where, to_where, time+"minutes", cost);
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
            dbmanager_train.insert(getUserID(), "lrt sripetaling", from_where, to_where, time+"minutes", cost);
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

