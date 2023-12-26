package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class info_flight extends AppCompatActivity {
    private Button button;
    private dbmanager_flight dbmanager_flight;
    private dbhelper_flight dbhelper_flight;
    private TextView flight_information_text;

    private String flight_number, departure_time;
    public int user_id=1; // Replace with the actual user ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_flight);

        button = findViewById(R.id.finish2);


        dbmanager_flight = new dbmanager_flight(this);
        dbhelper_flight = new dbhelper_flight(this);

        flight_information_text = findViewById(R.id.flight_information_text);

        //Specify Specific Data you want to get
        dbmanager_flight.open();

        String[] columnsToSelect = new String[]{
                dbhelper_flight.FLIGHT_NUMBER,
                dbhelper_flight.DEPARTURE_TIME,
        };

        //Retrieve the data
        Cursor cursor = dbmanager_flight.fetch(columnsToSelect, user_id);

        // Get column indices
        int flight_numberIndex = cursor.getColumnIndex(dbhelper_flight.FLIGHT_NUMBER);
        int departure_timeIndex = cursor.getColumnIndex(dbhelper_flight.DEPARTURE_TIME);

        // Check if column indices are valid
        if (flight_numberIndex >= 0 && departure_timeIndex >= 0) {
            // Extract values from the Cursor
            flight_number = cursor.getString(flight_numberIndex);
            departure_time = cursor.getString(flight_numberIndex);
        }

        //Display the data
        flight_information_text.setText("Your Flight Number is: " + flight_number + "\n" +
                                        "Your Departure Time is: " + departure_time);

        cursor.close();
        dbmanager_flight.close();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(info_flight.this, transport.class);
                startActivity(intent);
            }
        });
    }
}