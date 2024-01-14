package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class select_location extends AppCompatActivity implements View.OnClickListener {
    private static final int BUTTON_TAG_FIRST = 0;
    private static final int BUTTON_TAG_SECOND = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_location);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);

        ConstraintLayout parentLayout = findViewById(R.id.parent_layout);

        for (int i = 0; i < parentLayout.getChildCount(); i++) {
            View child = parentLayout.getChildAt(i);
            if (child instanceof Button) {
                int buttonId = child.getId();
                System.out.println("Button at position " + i + " has ID: " + getResources().getResourceEntryName(buttonId));
                if (i == 6) {
                    child.setTag(BUTTON_TAG_SECOND);
                } else {
                    child.setTag(BUTTON_TAG_FIRST);
                }
                child.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int buttonTag = (int) v.getTag();

        if (buttonTag == BUTTON_TAG_FIRST) {
            startActivity(new Intent(this, choose.class));
        } else {
            startActivity(new Intent(this, main.class));
        }
    }

}