package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class accommodation extends AppCompatActivity {
    private dbmanager_choose_accomodation dbmanager_choose_accomodation;
    //private Button bovelord_hotel;
    public int user_id=1, hotel_id;
    //public String input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accommodation);

        //set picture
        String hotel1 = "ampang";
        ImageButton img = findViewById(R.id.imageButton);
        img.setImageResource(getResources().getIdentifier(hotel1, "drawable", getPackageName()));

        //database
        dbmanager_choose_accomodation = new dbmanager_choose_accomodation(this);
    }

    public void bovelord_hotel(View view) {
        hotel_id=1;
        dbmanager_choose_accomodation.open();
        dbmanager_choose_accomodation.insert(hotel_id, user_id);
        dbmanager_choose_accomodation.close();
    }
    public void notification(View view) {
        Intent intent = new Intent (this, notification.class);
        startActivity(intent);
    }

    public void like(View view) {
        //like button
    }

    public void home(View view) {
        Intent intent = new Intent (this, main.class);
        startActivity(intent);
    }

    public void heart(View view) {
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
}