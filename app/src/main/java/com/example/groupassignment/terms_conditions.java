package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class terms_conditions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_conditions);

        //Top Navigation
        BaseActivity.setupToolbar(this);
    }

    public void OK(View view) {
        Intent intentFrom = getIntent();
        Intent intentTo = new Intent(this,sign_up.class);
        intentTo.putExtra("name", intentFrom.getStringExtra("name"));
        intentTo.putExtra("email", intentFrom.getStringExtra("email"));
        intentTo.putExtra("phone", intentFrom.getStringExtra("phone"));
        intentTo.putExtra("birthday", intentFrom.getStringExtra("birthday"));
        intentTo.putExtra("password", intentFrom.getStringExtra("password"));
        intentTo.putExtra("password2", intentFrom.getStringExtra("password2"));
        startActivity(intentTo);
    }

    public void onCheckboxClicked(View view) {
        sign_up.isChecked = ((CheckBox) view).isChecked();
    }
}

