package com.example.groupassignment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.groupassignment.R;

public class BaseActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static void setupToolbar(AppCompatActivity activity) {

        //apply top navigation
        androidx.appcompat.widget.Toolbar topnavi = activity.findViewById(R.id.topnavi);
        activity.setSupportActionBar(topnavi);

        //customize top navigation
    //   Context context = activity.getApplicationContext();
      //  activity.getSupportActionBar().setTitle("BookSwift");
     //   activity.getSupportActionBar().setIcon(ContextCompat.getDrawable(context, R.drawable.logo));
    }
}
