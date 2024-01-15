package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class train_type extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_train dbmanager_train;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_type);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);
    }

    public void kajang(View v){
        startActivity(new Intent(train_type.this, mrt_kajang.class));
    }

    public void ampang(View v) {
        startActivity(new Intent(train_type.this, lrt_sripetaling.class));
    }

    public void sri_petaling(View v) {
        startActivity(new Intent(train_type.this, lrt_ampang.class));
    }

    public void putrajaya(View v) {
        startActivity(new Intent(train_type.this, mrt_putrajaya.class));
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
}