package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.net.Uri;
import android.widget.VideoView;

import com.example.groupassignment.dbhelpers.dbhelper_accomodation_info;
import com.example.groupassignment.dbhelpers.dbhelper_airline_info;
import com.example.groupassignment.dbhelpers.dbhelper_book_history;
import com.example.groupassignment.dbhelpers.dbhelper_book_summary;
import com.example.groupassignment.dbhelpers.dbhelper_bus;
import com.example.groupassignment.dbhelpers.dbhelper_choose_accomodation;
import com.example.groupassignment.dbhelpers.dbhelper_choose_airline;
import com.example.groupassignment.dbhelpers.dbhelper_choose_bus;
import com.example.groupassignment.dbhelpers.dbhelper_choose_food;
import com.example.groupassignment.dbhelpers.dbhelper_choose_play;
import com.example.groupassignment.dbhelpers.dbhelper_choose_transport;
import com.example.groupassignment.dbhelpers.dbhelper_favourite;
import com.example.groupassignment.dbhelpers.dbhelper_filter_range;
import com.example.groupassignment.dbhelpers.dbhelper_flight;
import com.example.groupassignment.dbhelpers.dbhelper_food_info;
import com.example.groupassignment.dbhelpers.dbhelper_hotel_neccessity;
import com.example.groupassignment.dbhelpers.dbhelper_login_history;
import com.example.groupassignment.dbhelpers.dbhelper_message;
import com.example.groupassignment.dbhelpers.dbhelper_notification;
import com.example.groupassignment.dbhelpers.dbhelper_pax;
import com.example.groupassignment.dbhelpers.dbhelper_plan_history;
import com.example.groupassignment.dbhelpers.dbhelper_plan_summary;
import com.example.groupassignment.dbhelpers.dbhelper_play_info;
import com.example.groupassignment.dbhelpers.dbhelper_terms_conditions;
import com.example.groupassignment.dbhelpers.dbhelper_train;
import com.example.groupassignment.dbhelpers.dbhelper_transport_info;
import com.example.groupassignment.dbhelpers.dbhelper_user;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_airline_info;
import com.example.groupassignment.dbmanagers.dbmanager_bus;
import com.example.groupassignment.dbmanagers.dbmanager_food_info;
import com.example.groupassignment.dbmanagers.dbmanager_play_info;
import com.example.groupassignment.dbmanagers.dbmanager_accomodation_info;

public class main extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private SQLiteDatabase database;
    private com.example.groupassignment.dbmanagers.dbmanager_airline_info dbmanager_airline_info;
    private com.example.groupassignment.dbmanagers.dbmanager_bus dbmanager_bus;
    private com.example.groupassignment.dbmanagers.dbmanager_food_info dbmanager_food_info;
    private com.example.groupassignment.dbmanagers.dbmanager_play_info dbmanager_play_info;
    private com.example.groupassignment.dbmanagers.dbmanager_accomodation_info dbmanager_accomodation_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);




        //get latest version
        SharedPreferences preferences = getSharedPreferences("DatabasePrefs", Context.MODE_PRIVATE);
        for (int i=0; i<preferences.getInt("LatestDBVersion", 0); i++){
            updateVersion();
        }

        int version=0;

        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        version=dbhelper_login_history.DB_VERSION;
        dbmanager_login_history.close();
        main.updateVersion();

        if (version == 1) {
            initializeData();
        }
    }

    public void plan(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int login_id=Integer.parseInt(cursor.getString(0));
        String status=cursor.getString(3);
        cursor.close();
        if (status.equals("logged out")) {
            dbmanager_login_history.update(login_id, "plan", "logged out");
        }
        else {
            dbmanager_login_history.update(login_id, "plan", "logged in");
        }
        dbmanager_login_history.close();
        main.updateVersion();
        Intent intent = new Intent(this,select_location.class);
        startActivity(intent);
    }

    public void Book(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int login_id=Integer.parseInt(cursor.getString(0));
        String status=cursor.getString(3);
        cursor.close();
        if (status.equals("logged in")) {
            dbmanager_login_history.update(login_id, "book", "logged in");
            dbmanager_login_history.close();
            main.updateVersion();
            Intent intent = new Intent(this,select_location.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "You are not logged in.", Toast.LENGTH_SHORT).show();
            dbmanager_login_history.close();
            main.updateVersion();
        }
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
        if (status.equals("logged out")) {
            Intent intent = new Intent(this, log_in.class);
            startActivity(intent);
        }
        else if (status.equals("logged in")) {
            Intent intent = new Intent(this, profile.class);
            startActivity(intent);
        }
    }
    public void home(View view) {
        startActivity(new Intent(this, book_history.class));
    }
    public void notification(View view) {
        startActivity(new Intent(this, notification.class));
    }
    public void heart(View view) {
        startActivity(new Intent(this, my_favourite.class));
    }
    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }

    public static void updateVersion(){
        dbhelper_accomodation_info.DB_VERSION++;
        dbhelper_airline_info.DB_VERSION++;
        dbhelper_book_history.DB_VERSION++;
        dbhelper_book_summary.DB_VERSION++;
        dbhelper_bus.DB_VERSION++;
        dbhelper_choose_accomodation.DB_VERSION++;
        dbhelper_choose_airline.DB_VERSION++;
        dbhelper_choose_bus.DB_VERSION++;
        dbhelper_choose_food.DB_VERSION++;
        dbhelper_choose_play.DB_VERSION++;
        dbhelper_choose_transport.DB_VERSION++;
        dbhelper_favourite.DB_VERSION++;
        dbhelper_filter_range.DB_VERSION++;
        dbhelper_flight.DB_VERSION++;
        dbhelper_food_info.DB_VERSION++;
        dbhelper_hotel_neccessity.DB_VERSION++;
        dbhelper_login_history.DB_VERSION++;
        dbhelper_message.DB_VERSION++;
        dbhelper_notification.DB_VERSION++;
        dbhelper_pax.DB_VERSION++;
        dbhelper_plan_history.DB_VERSION++;
        dbhelper_plan_summary.DB_VERSION++;
        dbhelper_play_info.DB_VERSION++;
        dbhelper_terms_conditions.DB_VERSION++;
        dbhelper_train.DB_VERSION++;
        dbhelper_transport_info.DB_VERSION++;
        dbhelper_user.DB_VERSION++;
    }

    //save version to shared_prefs
    public static void saveVersion(Context context){
        SharedPreferences preferences = context.getSharedPreferences("DatabasePrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("LatestDBVersion", dbhelper_user.DB_VERSION);
        editor.apply();
    }

    public void initializeData(){
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        dbmanager_login_history.insert(1, "logged out", "null");
        dbmanager_login_history.close();
        main.updateVersion();

        dbmanager_airline_info = new dbmanager_airline_info(this);
        dbmanager_airline_info.open();
        dbmanager_airline_info.insert("Air Asia", 100);
        dbmanager_airline_info.insert("Firefly", 100);
        dbmanager_airline_info.insert("Berjaya Air", 100);
        dbmanager_airline_info.close();
        main.updateVersion();

        dbmanager_bus = new dbmanager_bus(this);
        dbmanager_bus.open();
        dbmanager_bus.insert(30, "Soutern");
        dbmanager_bus.insert( 30, "Mayang Sari");
        dbmanager_bus.insert(30, "City Express");
        dbmanager_bus.close();
        main.updateVersion();

        dbmanager_food_info = new dbmanager_food_info(this);
        dbmanager_food_info.open();
        dbmanager_food_info.insert("Cielo KL", 150, "cielo_kl");
        dbmanager_food_info.insert("Horizon Grill", 130, "horizon_grill");
        dbmanager_food_info.insert("Sky Bar", 180, "sky_bar");
        dbmanager_food_info.close();
        main.updateVersion();

        dbmanager_play_info = new dbmanager_play_info(this);
        dbmanager_play_info.open();
        dbmanager_play_info.insert("Sunway Lagoo", 100, "sunway_lagoon");
        dbmanager_play_info.insert("Genting Highland", 100, "genting_highland");
        dbmanager_play_info.insert("Sky Mirror", 100, "sky_mirror");
        dbmanager_play_info.close();
        main.updateVersion();

        dbmanager_accomodation_info = new dbmanager_accomodation_info(this);
        dbmanager_accomodation_info.open();
        dbmanager_accomodation_info.insert("Bevelord Hotel", 300, "bevelord_hotel");
        dbmanager_accomodation_info.insert("Crystal Hotel", 350, "crystal_hotel");
        dbmanager_accomodation_info.insert("Ocean Hotel", 250, "ocean_hotel");
        dbmanager_accomodation_info.close();
        main.updateVersion();
    }
}