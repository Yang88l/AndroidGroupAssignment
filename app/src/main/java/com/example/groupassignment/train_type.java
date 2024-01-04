package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class train_type extends AppCompatActivity {
    private dbmanager_train dbmanager_train;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_type);
    }

    public void kajang(View v){
        startActivity(new Intent(train_type.this, mrt_kajang.class));
    }

    public void ampang(View v) {
        startActivity(new Intent(train_type.this, lrt_ampang.class));
    }

    public void sri_petaling(View v) {
        startActivity(new Intent(train_type.this, lrt_sripetaling.class));
    }

    public void putrajaya(View v) {
        startActivity(new Intent(train_type.this, mrt_putrajaya.class));
    }
}