package com.example.groupassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.groupassignment.dbmanagers.dbmanager_login_history;
import com.example.groupassignment.dbmanagers.dbmanager_book_history;

import java.util.ArrayList;

public class book_history extends AppCompatActivity {
    private com.example.groupassignment.dbmanagers.dbmanager_book_history dbmanager_book_history;
    private com.example.groupassignment.dbhelpers.dbhelper_book_history dbhelper_book_history;
    private com.example.groupassignment.dbmanagers.dbmanager_login_history dbmanager_login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_history);

        ArrayList<String> namesList = new ArrayList<>();
        ArrayList<String> pricesList = new ArrayList<>();
        //ArrayList<String> imagesList = new ArrayList<>();

        dbmanager_book_history = new dbmanager_book_history(this);
        dbmanager_book_history.open();
        Cursor cursor = dbmanager_book_history.fetch(getUserID());

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String itemName = cursor.getString(2);
                String itemPrice = cursor.getString(3);

                namesList.add(itemName);
                pricesList.add(itemPrice);
            } while (cursor.moveToNext());
        }

        String[] names = namesList.toArray(new String[0]);
        String[] prices = pricesList.toArray(new String[0]);
        //String[] images = imagesList.toArray(new String[0]);

        ListView simpleList = findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(this, names, prices);
        simpleList.setAdapter(customAdapter);

        //Top Navigation
        BaseActivity.setupToolbar(this);

        //Background
        background.video(this);

        dbmanager_book_history.close();
        main.updateVersion();
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
        startActivity(new Intent(this, profile.class));
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

    public int getLoginID(){
        dbmanager_login_history = new dbmanager_login_history(this);
        dbmanager_login_history.open();
        Cursor cursor_login = dbmanager_login_history.fetch();
        cursor_login.moveToLast();
        int login_id=Integer.parseInt(cursor_login.getString(0));
        cursor_login.close();
        dbmanager_login_history.close();
        main.updateVersion();
        return login_id;
    }
}