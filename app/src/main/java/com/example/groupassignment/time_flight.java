package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class time_flight extends AppCompatActivity {

    private dbmanager_flight dbmanager_flight;
    private dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_flight);
        /*
        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();

        String[] columnsToSelect = new String[]{
                dbhelper_flight.TIME,
        };

        //Retrieve the data
        Cursor cursor = dbmanager_flight.fetch(user_id);

        // Get column indices
        int dateIndex = cursor.getColumnIndex(dbhelper_flight.TIME);

        // Check if column indices are valid
        if (dateIndex >= 0) {
            // Extract values from the Cursor
            date = cursor.getString(dateIndex);
        }

        cursor.close();
        dbmanager_flight.close();
*/
    }

    public void profile(View view) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this, book_history.class);
        startActivity(intent);
    }

    public void main(View view) {
        Intent intent = new Intent(this, main.class);
        startActivity(intent);
    }

    public void heart(View view) {
        Intent intent = new Intent(this, my_favourite.class);
        startActivity(intent);
    }

    public void next(View view) {
        EditText time = findViewById(R.id.time);

        dbmanager_flight = new dbmanager_flight(this);
        dbmanager_flight.open();
        Cursor cursor = dbmanager_flight.fetch(1);
        cursor.moveToLast();
        dbmanager_flight.update(Integer.parseInt(cursor.getString(0)), String.valueOf(time), null,null, null, Integer.parseInt(cursor.getString(5)));
        cursor.close();
        dbmanager_flight.close();
        main.updateVersion();
        Intent intent = new Intent(this, pax_flight.class);
        startActivity(intent);
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