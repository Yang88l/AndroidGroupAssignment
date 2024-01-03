package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class planning_summary extends AppCompatActivity {

    private dbmanager_plan_summary dbmanager_plan_summary;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;
    private com.example.groupassignment.dbmanager_accomodation_info dbmanager_accomodation_info;
    private com.example.groupassignment.dbmanager_food_info dbmanager_food_info;
    private com.example.groupassignment.dbmanager_play_info dbmanager_play_info;
    private com.example.groupassignment.dbmanager_flight dbmanager_flight;
    private com.example.groupassignment.dbmanager_bus dbmanager_bus;
    private com.example.groupassignment.dbmanager_plan_history dbmanager_plan_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning_summary);

        TextView contentLeft = findViewById(R.id.textView18);
        TextView contentRight = findViewById(R.id.textView19);

        dbmanager_plan_summary = new dbmanager_plan_summary(this);
        dbmanager_plan_summary.open();
        Cursor cursor_summary = dbmanager_plan_summary.fetch();
        cursor_summary.moveToLast();

        String type = cursor_summary.getString(1);
        int activity_id = Integer.parseInt(cursor_summary.getString(2));

        cursor_summary.close();

        StringBuilder displayText1 = new StringBuilder();
        StringBuilder displayText2 = new StringBuilder();

        if (cursor_summary != null && cursor_summary.moveToFirst()) {
            do {
                if (type.equals("hotel")) {
                    dbmanager_accomodation_info = new dbmanager_accomodation_info(this);
                    dbmanager_accomodation_info.open();
                    Cursor cursor = dbmanager_accomodation_info.fetch(activity_id);
                    displayText1.append(cursor.getString(1));
                    displayText2.append("RM"+cursor.getString(2));
                    dbmanager_accomodation_info.close();
                    main.updateVersion();
                }
                else if (type.equals("food")) {
                    dbmanager_food_info = new dbmanager_food_info(this);
                    dbmanager_food_info.open();
                    Cursor cursor = dbmanager_food_info.fetch(activity_id);
                    displayText1.append(cursor.getString(1));
                    displayText2.append("RM"+cursor.getString(2));
                    dbmanager_food_info.close();
                    main.updateVersion();
                }
                else if (type.equals("play")) {
                    dbmanager_play_info = new dbmanager_play_info(this);
                    dbmanager_play_info.open();
                    Cursor cursor = dbmanager_play_info.fetch(activity_id);
                    displayText1.append(cursor.getString(1));
                    displayText2.append("RM"+cursor.getString(2));
                    dbmanager_play_info.close();
                    main.updateVersion();
                }
                else if (type.equals("flight")) {
                    dbmanager_flight = new dbmanager_flight(this);
                    dbmanager_flight.open();
                    Cursor cursor = dbmanager_flight.fetch(activity_id);
                    displayText1.append(cursor.getString(1));
                    displayText2.append("RM"+cursor.getString(2));
                    dbmanager_flight.close();
                    main.updateVersion();
                }
                else if (type.equals("bus")){
                    dbmanager_bus = new dbmanager_bus(this);
                    dbmanager_bus.open();
                    Cursor cursor = dbmanager_bus.fetch(activity_id);
                    displayText1.append(cursor.getString(1));
                    displayText2.append("RM"+cursor.getString(2));
                    dbmanager_bus.close();
                    main.updateVersion();
                }

                if (!cursor_summary.isLast()) {
                    displayText1.append("\n");
                    displayText2.append("\n");
                }
            } while (cursor_summary.moveToNext());
            cursor_summary.close();
        }

        contentLeft.setText(displayText1);
        contentRight.setText(displayText2);

        //get user id
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor = dbmanager_login_history.fetch();
        cursor.moveToLast();
        int user_id=Integer.parseInt(cursor.getString(1));
        dbmanager_login_history.close();
        main.updateVersion();

        dbmanager_plan_history = new dbmanager_plan_history(this);
        dbmanager_plan_history.open();
        dbmanager_plan_history.insert(user_id, displayText1.toString(), displayText2.toString());
        dbmanager_plan_history.close();
        main.updateVersion();
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

    public void choose(View view) {
        Intent intent = new Intent(this,choose.class);
        startActivity(intent);
    }

    public void book(View view) {
        Intent intent = new Intent(this,payment_method.class);
        startActivity(intent);
    }

    public void notification(View view) {
        Intent intent = new Intent(this,notification.class);
        startActivity(intent);
    }

}