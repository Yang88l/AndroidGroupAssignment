package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class lrt_sripetaling extends AppCompatActivity {
    private dbmanager_train dbmanager_train;
    private com.example.groupassignment.dbmanager_login_history dbmanager_login_history;
    private String location, from_where, to_where;
    public int pos, from_pos, to_pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lrt_sripetaling);
    }

    //Functions
    public void sri_petaling(View view) {
        location = "Sri Petaling";
        pos=1;
        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void bukit_jalil(View view) {
        location = "Bukit Jalil";
        pos=2;
        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void sungai_besi(View view) {
        location = "Sungai Besi";
        pos=3;
        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void tasik_selatan(View view) {
        location = "Tasik Selatan";
        pos=4;
        Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
    }
    public void bandar_tun_razak(View view) {
        location = "Bandar Tun Razak";
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
            dbmanager_train.insert(getUserID(), "lrt sripetaling", from_where, to_where, time+"minutes", cost);
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
            dbmanager_train.insert(getUserID(), "lrt sripetaling", from_where, to_where, time+"minutes", cost);
            dbmanager_train.close();
            main.updateVersion();
        }
        else if (from_pos == to_pos) Toast.makeText(this, "Invalid location. Select different location.", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Please select a location", Toast.LENGTH_SHORT).show();
    }
}

