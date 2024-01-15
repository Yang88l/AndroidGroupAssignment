package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.groupassignment.dbmanagers.dbmanager_accomodation_info;
import com.example.groupassignment.dbmanagers.dbmanager_book_summary;
import com.example.groupassignment.dbmanagers.dbmanager_choose_accomodation;
import com.example.groupassignment.dbmanagers.dbmanager_choose_food;
import com.example.groupassignment.dbmanagers.dbmanager_choose_play;
import com.example.groupassignment.dbmanagers.dbmanager_food_info;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_plan_summary;
import com.example.groupassignment.dbmanagers.dbmanager_play_info;

public class information extends AppCompatActivity {

    private com.example.groupassignment.dbmanagers.dbmanager_choose_accomodation dbmanager_choose_accomodation;
    private com.example.groupassignment.dbmanagers.dbmanager_choose_food dbmanager_choose_food;
    private com.example.groupassignment.dbmanagers.dbmanager_choose_play dbmanager_choose_play;
    private com.example.groupassignment.dbmanagers.dbmanager_accomodation_info dbmanager_accomodation_info;
    private com.example.groupassignment.dbmanagers.dbmanager_food_info dbmanager_food_info;
    private com.example.groupassignment.dbmanagers.dbmanager_play_info dbmanager_play_info;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private com.example.groupassignment.dbmanagers.dbmanager_plan_summary dbmanager_plan_summary;
    private com.example.groupassignment.dbmanagers.dbmanager_book_summary dbmanager_book_summary;
 private VideoView bg;
    private int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //background.video(this);
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


        Intent intent = getIntent();
        int _id = intent.getIntExtra("_id", 0);
        String from = intent.getStringExtra("from");

        Toast.makeText(this, _id+from, Toast.LENGTH_SHORT).show();

        Button button = findViewById(R.id.button19);

        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        String activity = cursor_login.getString(2);
        if (activity.equals("plan")) {
            button.setText("Add to Plan");
        }
        else if (activity.equals("book")) {
            button.setText("Book Now");
        }
        dbmanager_login_history.close();
        main.updateVersion();


        if (from.equals("hotel")) {
            dbmanager_accomodation_info = new dbmanager_accomodation_info(this);
            dbmanager_accomodation_info.open();
            Cursor cursor = dbmanager_accomodation_info.fetch(_id);

            //display name
            TextView name = findViewById(R.id.textView28);
            name.setText(cursor.getString(1));

            //display price
            TextView price = findViewById(R.id.textView29);
            price.setText("RM"+cursor.getString(2));

            //display image
            ImageView img = findViewById(R.id.output_user_choose);
            img.setImageResource(getResources().getIdentifier(cursor.getString(3) + "_180", "drawable", getPackageName()));
            cursor.close();
            dbmanager_accomodation_info.close();
            main.updateVersion();
        }
        else if (from.equals("food")) {
            dbmanager_food_info = new dbmanager_food_info(this);
            dbmanager_food_info.open();
            Cursor cursor = dbmanager_food_info.fetch(_id);

            //display name
            TextView name = findViewById(R.id.textView28);
            name.setText(cursor.getString(1));

            //display price
            TextView price = findViewById(R.id.textView29);
            price.setText("RM"+cursor.getString(2));

            //display image
            ImageView img = findViewById(R.id.output_user_choose);
            img.setImageResource(getResources().getIdentifier(cursor.getString(3) + "_180", "drawable", getPackageName()));
            cursor.close();
            dbmanager_food_info.close();
            main.updateVersion();
        }
        else if (from.equals("play")) {
            dbmanager_play_info = new dbmanager_play_info(this);
            dbmanager_play_info.open();
            Cursor cursor = dbmanager_play_info.fetch(_id);

            //display name
            TextView name = findViewById(R.id.textView28);
            name.setText(cursor.getString(1));

            //display price
            TextView price = findViewById(R.id.textView29);
            price.setText("RM"+cursor.getString(2));

            //display image
            ImageView img = findViewById(R.id.output_user_choose);
            img.setImageResource(getResources().getIdentifier(cursor.getString(3) + "_180", "drawable", getPackageName()));
            cursor.close();
            dbmanager_play_info.close();
            main.updateVersion();
        }
    }

    public void book(View view) {
        Intent intent = getIntent();
        int _id = intent.getIntExtra("_id", 0);
        String from = intent.getStringExtra("from");

        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        String activity = cursor_login.getString(2);
        int login_id = cursor_login.getInt(0);
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();
/*
        if (from.equals("hotel")) {
            dbmanager_choose_accomodation = new dbmanager_choose_accomodation(this);
            dbmanager_choose_accomodation.open();
            dbmanager_choose_accomodation.insert(_id, getUserID());
            dbmanager_choose_accomodation.close();
            main.updateVersion();
        }
        else if (from.equals("food")) {
            dbmanager_choose_food = new dbmanager_choose_food(this);
            dbmanager_choose_food.open();
            dbmanager_choose_food.insert(_id, getUserID());
            dbmanager_choose_food.close();
            main.updateVersion();
        }
        else if (from.equals("play")) {
            dbmanager_choose_play = new dbmanager_choose_play(this);
            dbmanager_choose_play.open();
            dbmanager_choose_play.insert(_id, getUserID());
            dbmanager_choose_play.close();
            main.updateVersion();
        }
*/
        if (activity.equals("plan")) {
            dbmanager_plan_summary = new dbmanager_plan_summary(this);
            dbmanager_plan_summary.open();
            dbmanager_plan_summary.insert(from, _id, getUserID(), login_id);
            dbmanager_plan_summary.close();
            main.updateVersion();

            intent = new Intent(this,planning_summary.class);
            startActivity(intent);
        }
        else if (activity.equals("book")) {
            dbmanager_book_summary = new dbmanager_book_summary(this);
            dbmanager_book_summary.open();
            dbmanager_book_summary.insert(from, _id, getUserID(), login_id); //add login_id
            dbmanager_book_summary.close();
            main.updateVersion();
            intent = new Intent(this,booking_summary.class);
            startActivity(intent);
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
        startActivity(new Intent(this, my_favourite.class));
    }

    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }

    public void profile(View view) {
        startActivity(new Intent(this, profile.class));
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