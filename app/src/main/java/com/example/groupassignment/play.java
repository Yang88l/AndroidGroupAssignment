package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class play extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        //set picture
        String sunway_lagoon_picture = "sunway_lagoon";
        ImageButton sunway_lagoon_img = findViewById(R.id.sunway_lagoon_picture);
        sunway_lagoon_img.setImageResource(getResources().getIdentifier(sunway_lagoon_picture, "drawable", getPackageName()));

        //set picture
        String genting_highland_picture = "genting_highland_77";
        ImageButton genting_highland_img = findViewById(R.id.genting_highland_picture);
        genting_highland_img.setImageResource(getResources().getIdentifier(genting_highland_picture, "drawable", getPackageName()));

        //set picture
        String sky_mirror_picture = "sky_mirror_picture_77";
        ImageButton sky_mirror_img = findViewById(R.id.sky_mirror_picture);
        sky_mirror_img.setImageResource(getResources().getIdentifier(sky_mirror_picture, "drawable", getPackageName()));

        //set picture
        String zoo_negara_picture = "zoo_negara_77";
        ImageButton zoo_negara_img = findViewById(R.id.zoo_negara_picture);
        zoo_negara_img.setImageResource(getResources().getIdentifier(zoo_negara_picture, "drawable", getPackageName()));

    }

    public void main(View view) {
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
    }

    public void favourite(View view) {
        Intent intent = new Intent(this,my_favourite.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this,book_history.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this,profile.class);
        startActivity(intent);
    }

    public void add(View view) {
    }

    public void detail(View view) {
        Intent intent = new Intent(this, information.class);
        startActivity(intent);
    }

    public void filter(View view) {
        Intent intent = new Intent(this,filter.class);
        startActivity(intent);
    }

    public void notification(View view) {
        Intent intent = new Intent(this,notification.class);
        startActivity(intent);
    }

    public void sunway(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 1);
        startActivity(intent);
    }

    public void genting(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 2);
        startActivity(intent);
    }

    public void sky(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 3);
        startActivity(intent);
    }

    public void zoo(View view) {
        Intent intent = new Intent (this, information.class);
        intent.putExtra("from", "play");
        intent.putExtra("_id", 4);
        startActivity(intent);
    }
}