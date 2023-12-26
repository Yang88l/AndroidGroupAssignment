package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class mrt_kajang extends AppCompatActivity {

    private com.example.groupassignment.dbmanager_train dbmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mrt_kajang);

        EditText from = findViewById(R.id.inputfrom);
        EditText to = findViewById(R.id.inputto);
        EditText go_return = findViewById(R.id.inputfrom2);
        EditText go = findViewById(R.id.inputfrom3);
        EditText each_arrival_time = findViewById(R.id.inputfrom3);

        dbmanager = new com.example.groupassignment.dbmanager_train(this);
        dbmanager.open();
        Cursor cursor = dbmanager.fetch();

        String location_name = "";

        cursor.moveToLast();
        location_name = cursor.getString(2);
        cursor.close();

        from.setText(location_name);
        to.setText(location_name);
        go_return.setText(location_name);
        go.setText(location_name);

        dbmanager.close();
    }

    //BOTTOM BUTTONS DIRECTORY
    public void home(View view) {
        Intent intent = new Intent(mrt_kajang.this, main.class);
        startActivity(intent);
    }
    public void heart(View view) {
        Intent intent = new Intent(mrt_kajang.this, my_favourite.class);
        startActivity(intent);
    }
    public void history(View view) {
        Intent intent = new Intent(mrt_kajang.this, book_history.class);
        startActivity(intent);
    }
    public void profile(View view) {
        Intent intent = new Intent(mrt_kajang.this, profile.class);
        startActivity(intent);
    }
}