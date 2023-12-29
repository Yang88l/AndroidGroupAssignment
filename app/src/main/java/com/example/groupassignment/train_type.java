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

        dbmanager_train = new dbmanager_train(this);
    }

            public void kajang(View v){
            String location_name = "kajang";
            dbmanager_train.open();
            dbmanager_train.insert(location_name);
            dbmanager_train.close();
            startActivity(new Intent(train_type.this, mrt_kajang.class));
            }


            public void ampang(View v) {
                String location_name = "ampang";
                dbmanager_train.open();
                dbmanager_train.insert(location_name);
                dbmanager_train.close();
        startActivity(new Intent(train_type.this, lrt_ampang.class));
            }

            public void sri_petaling(View v) {
                String location_name = "sri petaling";
                dbmanager_train.open();
                dbmanager_train.insert(location_name);
                dbmanager_train.close();
                startActivity(new Intent(train_type.this, mrt_putrajaya.class));
            }


            public void putrajaya(View v) {
                String location_name = "putrajaya";
                dbmanager_train.open();
                dbmanager_train.insert(location_name);
                dbmanager_train.close();
                startActivity(new Intent(train_type.this, lrt_sripetaling.class));
            }

}