package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.dbmanagers.dbmanager_accomodation_info;
import com.example.groupassignment.dbmanagers.dbmanager_favourite;
import com.example.groupassignment.dbmanagers.dbmanager_food_info;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_play_info;

public class my_favourite extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_favourite dbmanager_favourite;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private com.example.groupassignment.dbmanagers.dbmanager_accomodation_info dbmanager_accomodation_info;
    private com.example.groupassignment.dbmanagers.dbmanager_food_info dbmanager_food_info;
    private com.example.groupassignment.dbmanagers.dbmanager_play_info dbmanager_play_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_favourite);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        boolean hotel_1 = false;
        boolean hotel_2 = false;
        boolean hotel_3 = false;

        TextView name1 = findViewById(R.id.text_1);
        ImageView picture1 = findViewById(R.id.image_1);
        TextView name2 = findViewById(R.id.text_2);
        ImageView picture2 = findViewById(R.id.image_2);
        TextView name3 = findViewById(R.id.text_3);
        ImageView picture3 = findViewById(R.id.image_3);

        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        Cursor cursor1 = dbmanager_favourite.fetch(getUserID(), "hotel", 1);
        if (cursor1.getString(4).equals("1")) hotel_1 = true;
        cursor1.close();
        Cursor cursor2 = dbmanager_favourite.fetch(getUserID(), "hotel", 2);
        if (cursor2.getString(4).equals("1")) hotel_2 = true;
        cursor2.close();
        Cursor cursor3 = dbmanager_favourite.fetch(getUserID(), "hotel", 3);
        if (cursor3.getString(4).equals("1")) hotel_3 = true;
        cursor3.close();
        dbmanager_favourite.close();
        main.updateVersion();

        dbmanager_accomodation_info = new dbmanager_accomodation_info(this);
        dbmanager_accomodation_info.open();
        if (hotel_1||hotel_2||hotel_3) {
            if (hotel_1) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(1);
                name1.setText(cursor_hotel.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
            if (hotel_2) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(2);
                name1.setText(cursor_hotel.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
            if (hotel_3) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(3);
                name1.setText(cursor_hotel.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout6); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        if (hotel_3) {
            if (hotel_1) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(1);
                name2.setText(cursor_hotel.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
            if (hotel_2) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(2);
                name2.setText(cursor_hotel.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
        }
        else if (hotel_2) {
            if (hotel_1) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(1);
                name2.setText(cursor_hotel.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout8); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        if (hotel_1&&hotel_2&&hotel_3) {
            if (hotel_1) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(1);
                name3.setText(cursor_hotel.getString(1));
                picture3.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout9); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        dbmanager_accomodation_info.close();
        main.updateVersion();
    }

    public void profile(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        String status=cursor.getString(3);
        cursor.close();
        dbmanager_login_history.close();
        main.updateVersion();
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        if (status.equals("logged out")) {
            Intent intent = new Intent(this, log_in.class);
            startActivity(intent);
        }
        else if (status.equals("logged in")) {
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
        }
    }
    public void notification(View view) {
        startActivity(new Intent(this, notification.class));
    }
    public void home(View view) {
        startActivity(new Intent(this, main.class));
    }
    public void heart(View view) {
        startActivity(new Intent(this, my_favourite.class));
    }
    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }

    public int getUserID(){
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        int user_id=Integer.parseInt(cursor_login.getString(1));
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();
        return user_id;
    }

    public void hotel(View view) {
        boolean hotel_1 = false;
        boolean hotel_2 = false;
        boolean hotel_3 = false;

        TextView name1 = findViewById(R.id.text_1);
        ImageView picture1 = findViewById(R.id.image_1);
        TextView name2 = findViewById(R.id.text_2);
        ImageView picture2 = findViewById(R.id.image_2);
        TextView name3 = findViewById(R.id.text_3);
        ImageView picture3 = findViewById(R.id.image_3);

        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        Cursor cursor1 = dbmanager_favourite.fetch(getUserID(), "hotel", 1);
        if (cursor1.getString(4).equals("1")) hotel_1 = true;
        Cursor cursor2 = dbmanager_favourite.fetch(getUserID(), "hotel", 2);
        if (cursor2.getString(4).equals("1")) hotel_2 = true;
        Cursor cursor3 = dbmanager_favourite.fetch(getUserID(), "hotel", 3);
        if (cursor3.getString(4).equals("1")) hotel_3 = true;
        cursor1.close();
        cursor2.close();
        cursor3.close();
        dbmanager_favourite.close();
        main.updateVersion();

        dbmanager_accomodation_info = new dbmanager_accomodation_info(this);
        dbmanager_accomodation_info.open();

        if (hotel_1||hotel_2||hotel_3) {
            if (hotel_1) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(1);
                name1.setText(cursor_hotel.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
            if (hotel_2) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(2);
                name1.setText(cursor_hotel.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
            if (hotel_3) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(3);
                name1.setText(cursor_hotel.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout6); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        if (hotel_3) {
            if (hotel_1) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(1);
                name2.setText(cursor_hotel.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
            if (hotel_2) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(2);
                name2.setText(cursor_hotel.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
        }
        else if (hotel_2) {
            if (hotel_1) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(1);
                name2.setText(cursor_hotel.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout8); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        if (hotel_1&&hotel_2&&hotel_3) {
            if (hotel_1) {
                Cursor cursor_hotel = dbmanager_accomodation_info.fetch(1);
                name3.setText(cursor_hotel.getString(1));
                picture3.setImageResource(getResources().getIdentifier(cursor_hotel.getString(3) + "_77", "drawable", getPackageName()));
                cursor_hotel.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout9); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        dbmanager_accomodation_info.close();
        main.updateVersion();
    }

    public void food(View view) {
        boolean food_1 = false;
        boolean food_2 = false;
        boolean food_3 = false;

        TextView name1 = findViewById(R.id.text_1);
        ImageView picture1 = findViewById(R.id.image_1);
        TextView name2 = findViewById(R.id.text_2);
        ImageView picture2 = findViewById(R.id.image_2);
        TextView name3 = findViewById(R.id.text_3);
        ImageView picture3 = findViewById(R.id.image_3);

        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        Cursor cursor1 = dbmanager_favourite.fetch(getUserID(), "food", 1);
        if (cursor1.getString(4).equals("1")) food_1 = true;
        Cursor cursor2 = dbmanager_favourite.fetch(getUserID(), "food", 2);
        if (cursor2.getString(4).equals("1")) food_2 = true;
        Cursor cursor3 = dbmanager_favourite.fetch(getUserID(), "food", 3);
        if (cursor3.getString(4).equals("1")) food_3 = true;
        cursor1.close();
        cursor2.close();
        cursor3.close();
        dbmanager_favourite.close();
        main.updateVersion();

        dbmanager_food_info = new dbmanager_food_info(this);
        dbmanager_food_info.open();

        if (food_1||food_2||food_3) {
            if (food_1) {
                Cursor cursor_food = dbmanager_food_info.fetch(1);
                name1.setText(cursor_food.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_food.getString(3) + "_77", "drawable", getPackageName()));
                cursor_food.close();
            }
            if (food_2) {
                Cursor cursor_food = dbmanager_food_info.fetch(2);
                name1.setText(cursor_food.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_food.getString(3) + "_77", "drawable", getPackageName()));
                cursor_food.close();
            }
            if (food_3) {
                Cursor cursor_food = dbmanager_food_info.fetch(3);
                name1.setText(cursor_food.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_food.getString(3) + "_77", "drawable", getPackageName()));
                cursor_food.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout6); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        if (food_3) {
            if (food_1) {
                Cursor cursor_food = dbmanager_food_info.fetch(1);
                name2.setText(cursor_food.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_food.getString(3) + "_77", "drawable", getPackageName()));
                cursor_food.close();
            }
            if (food_2) {
                Cursor cursor_food = dbmanager_food_info.fetch(2);
                name2.setText(cursor_food.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_food.getString(3) + "_77", "drawable", getPackageName()));
                cursor_food.close();
            }
        }
        else if (food_2) {
            if (food_1) {
                Cursor cursor_food = dbmanager_food_info.fetch(1);
                name2.setText(cursor_food.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_food.getString(3) + "_77", "drawable", getPackageName()));
                cursor_food.close();

            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout8); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        if (food_1&&food_2&&food_3) {
            if (food_1) {
                Cursor cursor_food = dbmanager_food_info.fetch(1);
                name3.setText(cursor_food.getString(1));
                picture3.setImageResource(getResources().getIdentifier(cursor_food.getString(3) + "_77", "drawable", getPackageName()));
                cursor_food.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout9); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        dbmanager_food_info.close();
        main.updateVersion();
    }

    public void play(View view) {
        boolean play_1 = false;
        boolean play_2 = false;
        boolean play_3 = false;

        TextView name1 = findViewById(R.id.text_1);
        ImageView picture1 = findViewById(R.id.image_1);
        TextView name2 = findViewById(R.id.text_2);
        ImageView picture2 = findViewById(R.id.image_2);
        TextView name3 = findViewById(R.id.text_3);
        ImageView picture3 = findViewById(R.id.image_3);

        dbmanager_favourite = new dbmanager_favourite(this);
        dbmanager_favourite.open();
        Cursor cursor1 = dbmanager_favourite.fetch(getUserID(), "play", 1);
        if (cursor1.getString(4).equals("1")) play_1 = true;
        Cursor cursor2 = dbmanager_favourite.fetch(getUserID(), "play", 2);
        if (cursor2.getString(4).equals("1")) play_2 = true;
        Cursor cursor3 = dbmanager_favourite.fetch(getUserID(), "play", 3);
        if (cursor3.getString(4).equals("1")) play_3 = true;
        cursor1.close();
        cursor2.close();
        cursor3.close();
        dbmanager_favourite.close();
        main.updateVersion();

        dbmanager_play_info = new dbmanager_play_info(this);
        dbmanager_play_info.open();

        if (play_1||play_2||play_3) {
            if (play_1) {
                Cursor cursor_play = dbmanager_play_info.fetch(1);
                name1.setText(cursor_play.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_play.getString(3) + "_77", "drawable", getPackageName()));
                cursor_play.close();
            }
            if (play_2) {
                Cursor cursor_play = dbmanager_play_info.fetch(2);
                name1.setText(cursor_play.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_play.getString(3) + "_77", "drawable", getPackageName()));
                cursor_play.close();
            }
            if (play_3) {
                Cursor cursor_play = dbmanager_play_info.fetch(3);
                name1.setText(cursor_play.getString(1));
                picture1.setImageResource(getResources().getIdentifier(cursor_play.getString(3) + "_77", "drawable", getPackageName()));
                cursor_play.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout6); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        if (play_3) {
            if (play_1) {
                Cursor cursor_play = dbmanager_play_info.fetch(1);
                name2.setText(cursor_play.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_play.getString(3) + "_77", "drawable", getPackageName()));
                cursor_play.close();
            }
            if (play_2) {
                Cursor cursor_play = dbmanager_play_info.fetch(2);
                name2.setText(cursor_play.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_play.getString(3) + "_77", "drawable", getPackageName()));
                cursor_play.close();
            }
        }
        else if (play_2) {
            if (play_1) {
                Cursor cursor_play = dbmanager_play_info.fetch(1);
                name2.setText(cursor_play.getString(1));
                picture2.setImageResource(getResources().getIdentifier(cursor_play.getString(3) + "_77", "drawable", getPackageName()));
                cursor_play.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout8); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        if (play_1&&play_2&&play_3) {
            if (play_1) {
                Cursor cursor_play = dbmanager_play_info.fetch(1);
                name3.setText(cursor_play.getString(1));
                picture3.setImageResource(getResources().getIdentifier(cursor_play.getString(3) + "_77", "drawable", getPackageName()));
                cursor_play.close();
            }
        }
        else {
            LinearLayout linearLayout = findViewById(R.id.linearLayout9); // Replace with your LinearLayout ID
            linearLayout.setVisibility(View.GONE);
        }

        dbmanager_play_info.close();
        main.updateVersion();
    }
}