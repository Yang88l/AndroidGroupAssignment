package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_airline_info;
import com.example.groupassignment.main;

public class dbmanager_airline_info {

    private dbhelper_airline_info dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_airline_info(Context c) {
        context = c;
    }

    public dbmanager_airline_info open() throws SQLException {
        dbHelper = new dbhelper_airline_info(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(String airline, double price) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_airline_info.AIRLINE, airline);
        contentValue.put(dbhelper_airline_info.PRICE, price);
        database.insert(dbhelper_airline_info.TABLE_NAME, null, contentValue);
    }

    // SELECT airline, price FROM table
    public Cursor fetch(int _id) {
        String[] columns = new String[] {
                dbhelper_airline_info.AIRLINE_ID,
                dbhelper_airline_info.AIRLINE,
                dbhelper_airline_info.PRICE
        };
        Cursor cursor = database.query(dbhelper_airline_info.TABLE_NAME, columns, "airline_id="+Integer.toString(_id), null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE airline, price FROM table WHERE airline_info_id = _id
    public int update(int _id, String airline, double price) {
        ContentValues contentValues = new ContentValues();
        if(airline!=null)contentValues.put(dbhelper_airline_info.AIRLINE, airline);
        else if(price!=-1)contentValues.put(dbhelper_airline_info.PRICE, price);
        int i = database.update(dbhelper_airline_info.TABLE_NAME, contentValues, dbhelper_airline_info.AIRLINE_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE airline_info_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_airline_info.TABLE_NAME, dbhelper_airline_info.AIRLINE_ID + "=" + _id, null);
    }
}