package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.groupassignment.dbmanagers.dbmanager_book_history;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_plan_history;
import com.example.groupassignment.dbmanagers.dbmanager_user;

public class payment_successful extends AppCompatActivity {
    private TextView price_text;
    private Intent intent;
    private double price;
    private payment payment;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
private VideoView bg;
    private int currentPosition;
    private com.example.groupassignment.dbmanagers.dbmanager_book_history dbmanager_book_history;
    private com.example.groupassignment.dbmanagers.dbmanager_plan_history dbmanager_plan_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_successful);

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

        //identify account
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int login_id = cursor.getInt(0);
        int user_id = cursor.getInt(1);
        cursor.close();
        dbmanager_login_history.close();
        main.updateVersion();

        //get price of user order
        dbmanager_book_history = new dbmanager_book_history(this);
        dbmanager_book_history.open();
        Cursor cursor2 = dbmanager_book_history.fetchWithLoginID(user_id, login_id);
        price = cursor2.getDouble(4);
        cursor2.close();
        dbmanager_book_history.close();
        main.updateVersion();

        //ASSIGN ID
        price_text = findViewById(R.id.price_text);

        //SET TEXT
        price_text.setText(String.format("RM%.2f", price));

    }

    //FINISH BUTTON
    public void finish(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_uid = dbmanager_login_history.fetch();
        cursor_uid.moveToLast();
        int user_id=Integer.parseInt(cursor_uid.getString(1));
        cursor_uid.close();
        dbmanager_login_history.update(user_id, null, "logged out");
        dbmanager_login_history.insert(user_id, "logged in", "null");
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        int login_id=Integer.parseInt(cursor_login.getString(0));
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();

        dbmanager_book_history = new dbmanager_book_history(this);
        dbmanager_book_history.open();
        dbmanager_book_history.insert(user_id, login_id, "", "");
        dbmanager_book_history.close();
        main.updateVersion();

        dbmanager_plan_history = new dbmanager_plan_history(this);
        dbmanager_plan_history.open();
        dbmanager_plan_history.insert(user_id, login_id, "", "");
        dbmanager_plan_history.close();
        main.updateVersion();

        Intent intent = new Intent(payment_successful.this, QRcode.class);
        intent.putExtra("price", price);
        startActivity(intent);
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

    public int getLoginID(){
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        int login_id=Integer.parseInt(cursor_login.getString(0));
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();
        return login_id;
    }
}