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

public class mrt_kajang extends AppCompatActivity {
    private dbmanager_train dbmanager_train;
    private dbhelper_train dbhelper_train;
    private TextView go_return_text, go_text, each_arrival_time_text, from_text, to_text;
    private Button from, to;
    public String[] location_name = new String[] {"Kajang"};
    public String  user_input;
    public int train_id, station_travelled, from_pos, to_pos;
    public double go_cost, go_return_cost;

    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lrt_sripetaling);

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
        dbmanager_train.update(1, "mrt kajang");

        Cursor cursor = dbmanager_train.fetch(location_name);

        train_id = cursor.getInt(0);

        //Display the data
        calculate();
        go_return_text.setText(String.valueOf(go_return_cost));
        go_text.setText(String.valueOf(go_cost));
        each_arrival_time_text.setText("5 Minutes");

        cursor.close();
        dbmanager_train.close();
    }


    //BOTTOM BUTTONS DIRECTORY
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
    //Functions
    public void kajang(View view) {
        if (user_input.equals("from")) {
            from.setText("Kajang");
            from_pos = 1;
        } else if (user_input.equals("to")) {
            to.setText("Kajang");
            to_pos = 1;
        } else if (user_input.equals("")) {
            Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
        }
    }
    public void stadium_kajang(View view) {
        if (user_input.equals("from")) {
            from.setText("Stadium Kajang");
            from_pos = 2;
        } else if (user_input.equals("to")) {
            to.setText("Stadium Kajang");
            to_pos = 2;
        } else if (user_input.equals("")) {
            Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
        }
    }
    public void sungai_jernih(View view) {
        if (user_input.equals("from")) {
            from.setText("Sungai Jernih");
            from_pos = 3;
        } else if (user_input.equals("to")) {
            to.setText("Sungai Jernih");
            to_pos = 3;
        } else if (user_input.equals("")) {
            Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
        }
    }
    public void bukit_dokong(View view) {
        if (user_input.equals("from")) {
            from.setText("Bukit Dokong");
            from_pos = 4;
        } else if (user_input.equals("to")) {
            to.setText("Bukit Dokong");
            to_pos = 4;
        } else if (user_input.equals("")) {
            Toast.makeText(this, "Please press from or to before selecting station", Toast.LENGTH_SHORT).show();
        }
    }
    public void batu_11_cheras(View view) {
        if (user_input.equals("from")) {
            from.setText("Batu 11 Cheras");
            from_pos = 5;
        } else if (user_input.equals("to")) {
            to.setText("Batu 11 Cheras");
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
        go_cost = (station_travelled * 0.30) + station_travelled;
        go_return_cost = go_cost * 2;
    }
}

