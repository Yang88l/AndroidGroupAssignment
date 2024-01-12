package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.groupassignment.dbmanagers.dbmanager_choose_airline;
import com.example.groupassignment.dbmanagers.dbmanager_login_history;

public class airline extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_choose_airline dbmanager_choose_airline;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airline);

        //Top Navigation
        BaseActivity.setupToolbar(this);
    }

    public void airasia(View view) {
        insertChooseAirline(1);
    }
    public void firefly(View view) {
        insertChooseAirline(2);
    }
    public void berjaya_air(View view) {
        insertChooseAirline(3);
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

    public void insertChooseAirline(int airline_id){
        dbmanager_choose_airline = new dbmanager_choose_airline(this);
        dbmanager_choose_airline.open();
        dbmanager_choose_airline.insert(airline_id, getUserID());
        dbmanager_choose_airline.close();
        main.updateVersion();
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
}