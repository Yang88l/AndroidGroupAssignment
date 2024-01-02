package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class lrt_sripetaling extends AppCompatActivity {
    private dbmanager_train dbmanager_train;
    private dbhelper_train dbhelper_train;
    private TextView go_return_text, go_text, each_arrival_time_text, from_text, to_text;
    private Button from, to;
    public String[] location_name = new String[] {"Sri Petaling"};
    public String user_input;
    public int train_id, station_travelled, from_pos, to_pos;
    public double go_cost, go_return_cost;

    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lrt_sripetaling);

        dbhelper_train.DB_VERSION = main.dbversion++;

        dbmanager_train = new dbmanager_train(this);
        dbhelper_train = new dbhelper_train(this);


        from_text = findViewById(R.id.from);
        to_text = findViewById(R.id.to);
        go_return_text = findViewById(R.id.go_return);
        go_text = findViewById(R.id.go);
        each_arrival_time_text = findViewById(R.id.each_arrival_time);
        from = findViewById(R.id.button_from);
        to = findViewById(R.id.button_to);


        //Specify Specific Data you want to get
        dbmanager_train.open();


        //Retrieve the data
        dbmanager_train.update(1, "lrt sri petaling");

        Cursor cursor = dbmanager_train.fetch(location_name);

        train_id = cursor.getInt(0);

        //Display the data
        calculate();
        go_return_text.setText(String.valueOf(go_return_cost));
        go_text.setText(String.valueOf(go_cost));
        each_arrival_time_text.setText("4 Minutes");

        cursor.close();
        dbmanager_train.close();
    }


    //BOTTOM BUTTONS DIRECTORY
    public void home(View view) {
        Intent intent = new Intent(lrt_sripetaling.this, main.class);
        startActivity(intent);
    }

    public void heart(View view) {
        Intent intent = new Intent(lrt_sripetaling.this, my_favourite.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(lrt_sripetaling.this, book_history.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(lrt_sripetaling.this, profile.class);
        startActivity(intent);
    }
    //Functions
    public void sri_petaling(View view) {
        if (user_input.equals("from")) {
            from.setText("Sri Petaling");
            from_pos = 1;
        } else if (user_input.equals("to")) {
            to.setText("Sri Petaling");
            to_pos = 1;
        } else if (user_input.equals("")) {
            Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
        }
    }
    public void bukit_jalil(View view) {
        if (user_input.equals("from")) {
            from.setText("Bukit Jalil");
            from_pos = 2;
        } else if (user_input.equals("to")) {
            to.setText("Bukit Jalil");
            to_pos = 2;
        } else if (user_input.equals("")) {
            Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
        }
    }
    public void sungai_besi(View view) {
        if (user_input.equals("from")) {
            from.setText("Sungai Besi");
            from_pos = 3;
        } else if (user_input.equals("to")) {
            to.setText("Sungai Besi");
            to_pos = 3;
        } else if (user_input.equals("")) {
            Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
        }
    }
    public void tasik_selatan(View view) {
        if (user_input.equals("from")) {
            from.setText("Tasik Selatan");
            from_pos = 4;
        } else if (user_input.equals("to")) {
            to.setText("Tasik Selatan");
            to_pos = 4;
        } else if (user_input.equals("")) {
            Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
        }
    }
    public void bandar_tun_razak(View view) {
        if (user_input.equals("from")) {
            from.setText("Bandar Tun Razak");
            from_pos = 5;
        } else if (user_input.equals("to")) {
            to.setText("Bandar Tun Razak");
            to_pos = 5;
        } else if (user_input.equals("")) {
            Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
        }
    }
    public void from(View view) {
        from.setText("From (selected)");
        user_input = "from";
    }
    public void to(View view) {
        to.setText("To (selected)");
        user_input = "to";
    }
    public void calculate() {
        for(station_travelled=1; from_pos < to_pos; from_pos++){
            station_travelled++;
        }
        go_cost = (station_travelled * 0.50) + station_travelled;
        go_return_cost = go_cost * 2;
    }
}

