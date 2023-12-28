package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class information extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_choose_play dbmanager_choose_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        dbmanager_choose_play = new dbmanager_choose_play(this);
    }

    public void book(View view) {
        int play_id=1, user_id=1;

        dbmanager_choose_play.open();
        dbmanager_choose_play.insert(play_id, user_id);
        dbmanager_choose_play.close();

        Intent intent = new Intent(this,choose.class);
        startActivity(intent);
    }


    public void main(View view) {
        Intent intent = new Intent (this, main.class);
        startActivity(intent);
    }

    public void favourite(View view) {
        Intent intent = new Intent (this, my_favourite.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent (this, book_history.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void notification(View view) {
        Intent intent = new Intent (this, notification.class);
        startActivity(intent);
    }
}