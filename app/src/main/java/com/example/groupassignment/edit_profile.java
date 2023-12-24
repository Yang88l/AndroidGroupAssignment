package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class edit_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
    }


    public void cancel_edit(View view) {
        Intent intent = new Intent (this, profile.class);
        startActivity(intent);
    }

    public void save_edit(View view) {

        EditText username = findViewById(R.id.editTextText4);
        EditText email_address = findViewById(R.id.editTextText5);
        EditText phone_number = findViewById(R.id.editTextPhone2);
        EditText birth_date = findViewById(R.id.editTextDate2);

        Intent intent = new Intent (this, profile.class);
        intent.putExtra("username",username.getText().toString());
        intent.putExtra("email_address",email_address.getText().toString());
        intent.putExtra("phone_number",phone_number.getText().toString());
        intent.putExtra("birth_date",birth_date.getText().toString());
        startActivity(intent);
    }

    public void edit_image(View view) {
        Intent intent = new Intent (this, set_picture.class);
        startActivity(intent);
    }
}