package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.dbmanagers.dbmanager_accomodation_info;
import com.example.groupassignment.dbmanagers.dbmanager_bus;
import com.example.groupassignment.dbmanagers.dbmanager_flight;
import com.example.groupassignment.dbmanagers.dbmanager_food_info;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_plan_history;
import com.example.groupassignment.dbmanagers.dbmanager_plan_summary;
import com.example.groupassignment.dbmanagers.dbmanager_play_info;

public class planning_summary extends AppCompatActivity {
    private dbmanager_plan_summary dbmanager_plan_summary;
    private dbmanager_login_history dbmanager_login_history;
    private dbmanager_accomodation_info dbmanager_accomodation_info;
    private dbmanager_food_info dbmanager_food_info;
    private dbmanager_play_info dbmanager_play_info;
    private dbmanager_flight dbmanager_flight;
    private dbmanager_bus dbmanager_bus;
    private dbmanager_plan_history dbmanager_plan_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning_summary);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);

        TextView contentLeft = findViewById(R.id.textView18);
        TextView contentRight = findViewById(R.id.textView19);

        StringBuilder displayText1 = new StringBuilder();
        StringBuilder displayText2 = new StringBuilder();

        dbmanager_plan_summary = new dbmanager_plan_summary(this);
        dbmanager_plan_summary.open();
        Cursor cursor_summary = dbmanager_plan_summary.fetch(getUserID());
        Toast.makeText(this, cursor_summary.getString(1), Toast.LENGTH_SHORT).show();
        if (cursor_summary != null && cursor_summary.moveToFirst()) {
            do {
                if (cursor_summary.getString(1).equals("hotel")){
                    dbmanager_accomodation_info = new dbmanager_accomodation_info(this);
                    dbmanager_accomodation_info.open();
                    Cursor cursor = dbmanager_accomodation_info.fetch(Integer.parseInt(cursor_summary.getString(2)));
                    Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
                    displayText1.append(cursor.getString(1));
                    displayText2.append(": RM"+cursor.getString(2));
                    cursor.close();
                    dbmanager_accomodation_info.close();
                    main.updateVersion();
                }
                else if (cursor_summary.getString(1).equals("food")){
                    dbmanager_food_info = new dbmanager_food_info(this);
                    dbmanager_food_info.open();
                    Cursor cursor = dbmanager_food_info.fetch(Integer.parseInt(cursor_summary.getString(2)));
                    displayText1.append(cursor.getString(1));
                    displayText2.append(": RM"+cursor.getString(2));
                    cursor.close();
                    dbmanager_food_info.close();
                    main.updateVersion();
                }
                else if (cursor_summary.getString(1).equals("play")){
                    dbmanager_play_info = new dbmanager_play_info(this);
                    dbmanager_play_info.open();
                    Cursor cursor = dbmanager_play_info.fetch(Integer.parseInt(cursor_summary.getString(2)));
                    displayText1.append(cursor.getString(1));
                    displayText2.append(": RM"+cursor.getString(2));
                    cursor.close();
                    dbmanager_play_info.close();
                    main.updateVersion();
                }
                if (!cursor_summary.isLast()) {
                    displayText1.append("\n");
                    displayText2.append("\n");
                }
            } while (cursor_summary.moveToNext());
        }

        cursor_summary.close();
        dbmanager_plan_summary.close();
        main.updateVersion();

        contentLeft.setText(displayText1.toString());
        contentRight.setText(displayText2.toString());

        dbmanager_plan_history = new dbmanager_plan_history(this);
        dbmanager_plan_history.open();
        dbmanager_plan_history.insert(getUserID(), displayText1.toString(), displayText2.toString());
        dbmanager_plan_history.close();
        main.updateVersion();
    }
    public void choose(View view) {
        Intent intent = new Intent(this,choose.class);
        startActivity(intent);
    }
    public void book(View view) {
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
    }
    public void notification(View view) { startActivity(new Intent(this, notification.class));}
    public void home(View view) {
        startActivity(new Intent(this, main.class));
    }

    public void heart(View view) {
        startActivity(new Intent(this, my_favourite.class));
    }

    public void history(View view) {
        startActivity(new Intent(this, book_history.class));
    }

    public void profile(View view) {
        startActivity(new Intent(this, profile.class));
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
}