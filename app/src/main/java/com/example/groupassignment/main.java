package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class main extends AppCompatActivity {
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
            dbmanager_login_history.close();
            Intent intent = new Intent(this,select_location.class);
            startActivity(intent);
        }
        dbmanager_login_history.close();
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
            Intent intent = new Intent(this,select_location.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "You are not logged in.", Toast.LENGTH_SHORT).show();
            dbmanager_login_history.close();
        }
    }

    public void profile(View view) {
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Toast.makeText(this, dbhelper_login_history.DB_VERSION+"", Toast.LENGTH_SHORT).show();
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
    public static int dbversion;
    public static void initializeVersion(){

    }
}