package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class food extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
    }



    public void like(View view) {
        // like the food
    }


    public void cielo_kl(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "food");
        intent.putExtra("_id", 1);
        startActivity(intent);
    }

    public void horizon_grill(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "food");
        intent.putExtra("_id", 2);
        startActivity(intent);
    }

    public void sky_bar(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "food");
        intent.putExtra("_id", 3);
        startActivity(intent);
    }
}