package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_train;

public class mrt_putrajaya extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_train dbmanager_train;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;
    private String location, from_where, to_where;
    public int pos, from_pos, to_pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mrt_putrajaya);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);
    }

    //Functions
    public void putrajaya_sentral(View view) {
        location = "Putrajaya Sentral";
        pos=1;
        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void cyberjaya_city_centre(View view) {
        location = "Cyberjaya City Centre";
        pos=2;
        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void cyberjaya_utara(View view) {
        location = "Cyberjaya Utara";
        pos=3;
        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void sierra(View view) {
        location = "16 Sierra";
        pos=4;
        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void putra_permai(View view) {
        location = "Putra Permai";
        pos=5;
        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }

    public void from(View view) {
        if (location==null) {
            Toast.makeText(this, "Please select a location", Toast.LENGTH_SHORT).show();
        }
        else {
            TextView from = findViewById(R.id.from);
            Button fromButton = findViewById(R.id.button_from);
            from.setText(location);
            fromButton.setText("From (selected)");
            from_pos=pos;
            from_where=location;
        }
    }

    public void to(View view) {
        if (location==null) {
            Toast.makeText(this, "Please select a location", Toast.LENGTH_SHORT).show();
        }
        else {
            TextView to = findViewById(R.id.to);
            Button toButton = findViewById(R.id.button_to);
            to.setText(location);
            toButton.setText("To (selected)");
            to_pos=pos;
            to_where=location;
        }
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

    public void calculate(View view) {
        TextView arrival_time = findViewById(R.id.time);
        TextView total_cost = findViewById(R.id.cost);
        int time;
        double cost;
        if (from_pos > to_pos) {
            time=(from_pos-to_pos)*5;
            cost=0.4+(double) (from_pos-to_pos)*0.4;
            arrival_time.setText(time+"minutes");
            total_cost.setText("RM"+cost);
            dbmanager_train = new dbmanager_train(this);
            dbmanager_train.open();
            dbmanager_train.insert(getUserID(), "mrt putrajaya", from_where, to_where, time+"minutes", cost);
            dbmanager_train.close();
            main.updateVersion();
        }
        else if (from_pos < to_pos) {
            time=(to_pos-from_pos)*5;
            cost=0.4+(double) (to_pos-from_pos)*0.4;
            arrival_time.setText(time+"minutes");
            total_cost.setText("RM"+cost);
            dbmanager_train = new dbmanager_train(this);
            dbmanager_train.open();
            dbmanager_train.insert(getUserID(), "mrt putrajaya", from_where, to_where, time+"minutes", cost);
            dbmanager_train.close();
            main.updateVersion();
        }
        else if (from_pos == to_pos) Toast.makeText(this, "Invalid location. Select different location.", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Please select a location", Toast.LENGTH_SHORT).show();
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
}

