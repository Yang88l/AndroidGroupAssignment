package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class information extends AppCompatActivity {

    private dbmanager_choose_accomodation dbmanager_choose_accomodation;
    private dbmanager_choose_food dbmanager_choose_food;
    private dbmanager_choose_play dbmanager_choose_play;
    private dbmanager_accomodation_info dbmanager_accomodation_info;
    private dbmanager_food_info dbmanager_food_info;
    private dbmanager_play_info dbmanager_play_info;
    private dbmanager_login_history dbmanager_login_history;
    private dbmanager_plan_summary dbmanager_plan_summary;
    private com.example.groupassignment.dbmanager_book_summary dbmanager_book_summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        Button button = findViewById(R.id.button19);
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        String activity = cursor_login.getString(2);
        if (activity.equals("plan")) {
            button.setText("Add to Plan");
        }
        else if (activity.equals("book")) {
            button.setText("Book Now");
        }
        dbmanager_login_history.close();

        Intent intent = getIntent();
        //get id
        int _id = Integer.parseInt(intent.getStringExtra("_id"));
        String from = intent.getStringExtra("from");

        if (from=="hotel") {
            //open database
            dbmanager_accomodation_info = new dbmanager_accomodation_info(this);
            dbmanager_accomodation_info.open();
            Cursor cursor = dbmanager_accomodation_info.fetch(_id);

            //display name
            TextView name = findViewById(R.id.textView28);
            name.setText(cursor.getString(1));

            //display price
            TextView price = findViewById(R.id.textView29);
            price.setText(cursor.getString(2));

            //display image
            ImageView img = findViewById(R.id.output_user_choose);
            img.setImageResource(getResources().getIdentifier(cursor.getString(3) + "_180", "drawable", getPackageName()));
            dbmanager_accomodation_info.close();
        }
        else if (from=="food") {
            //open database
            dbmanager_food_info = new dbmanager_food_info(this);
            dbmanager_food_info.open();
            Cursor cursor = dbmanager_food_info.fetch(_id);

            //display name
            TextView name = findViewById(R.id.textView28);
            name.setText(cursor.getString(1));

            //display price
            TextView price = findViewById(R.id.textView29);
            price.setText(cursor.getString(2));

            //display image
            ImageView img = findViewById(R.id.output_user_choose);
            img.setImageResource(getResources().getIdentifier(cursor.getString(3) + "_180", "drawable", getPackageName()));
            dbmanager_food_info.close();
        }
        else if (from=="play") {
            //open database
            dbmanager_play_info = new dbmanager_play_info(this);
            dbmanager_play_info.open();
            Cursor cursor = dbmanager_play_info.fetch(_id);

            //display name
            TextView name = findViewById(R.id.textView28);
            name.setText(cursor.getString(1));

            //display price
            TextView price = findViewById(R.id.textView29);
            price.setText(cursor.getString(2));

            //display image
            ImageView img = findViewById(R.id.output_user_choose);
            img.setImageResource(getResources().getIdentifier(cursor.getString(3) + "_180", "drawable", getPackageName()));
            dbmanager_play_info.close();
        }
    }

    public void book(View view) {
        Intent intent = getIntent();
        //get id
        int _id = Integer.parseInt(intent.getStringExtra("_id"));
        String from = intent.getStringExtra("from");

        //get activity
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        String activity = cursor_login.getString(2);

        //get user id
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(1));
        dbmanager_login_history.close();

        if (from=="hotel") {
            //open database
            dbmanager_choose_accomodation = new dbmanager_choose_accomodation(this);
            dbmanager_choose_accomodation.open();
            dbmanager_choose_accomodation.insert(_id, user_id);
            dbmanager_choose_accomodation.close();
        }
        else if (from=="food") {
            //open database
            dbmanager_choose_food = new dbmanager_choose_food(this);
            dbmanager_choose_food.open();
            dbmanager_choose_food.insert(_id, user_id);
            dbmanager_choose_food.close();
        }
        else if (from=="play") {
            //open database
            dbmanager_choose_play = new dbmanager_choose_play(this);
            dbmanager_choose_play.open();
            dbmanager_choose_play.insert(_id, user_id);
            dbmanager_choose_play.close();
        }

        if (activity=="plan") {
            dbmanager_plan_summary = new dbmanager_plan_summary(this);
            dbmanager_plan_summary.open();
            dbmanager_plan_summary.insert(from, _id, user_id);
            dbmanager_plan_summary.close();
            intent = new Intent(this,planning_summary.class);
            startActivity(intent);
        }
        else if (activity=="book") {
            dbmanager_book_summary = new dbmanager_book_summary(this);
            dbmanager_book_summary.open();
            dbmanager_book_summary.insert(from, _id, user_id);
            dbmanager_book_summary.close();
            intent = new Intent(this,booking_summary.class);
            startActivity(intent);
        }
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