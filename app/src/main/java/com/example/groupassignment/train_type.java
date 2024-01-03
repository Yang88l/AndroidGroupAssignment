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
        selectTrain("kajang");
    }

    public void ampang(View v) {
        selectTrain("ampang");
    }

    public void sri_petaling(View v) {
        selectTrain("sri petaling");
    }

    public void putrajaya(View v) {
        selectTrain("putrajaya");
    }

    public void selectTrain(String train){
        dbmanager_train = new dbmanager_train(this);
        dbmanager_train.open();
        dbmanager_train.insert(train);
        dbmanager_train.close();
        main.updateVersion();
        startActivity(new Intent(train_type.this, lrt_sripetaling.class));
    }
}