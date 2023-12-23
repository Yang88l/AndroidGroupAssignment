package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class lrt_ampang extends AppCompatActivity {
    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lrt_ampang);
    }




    //BOTTOM BUTTONS DIRECTORY
    public void home(View view) {
        Intent intent = new Intent(lrt_ampang.this, main.class);
        startActivity(intent);
    }
    public void heart(View view) {
        Intent intent = new Intent(lrt_ampang.this, my_favourite.class);
        startActivity(intent);
    }
    public void history(View view) {
        Intent intent = new Intent(lrt_ampang.this, book_history.class);
        startActivity(intent);
    }
    public void profile(View view) {
        Intent intent = new Intent(lrt_ampang.this, profile.class);
        startActivity(intent);
    }
}

