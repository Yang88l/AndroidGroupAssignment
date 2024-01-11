package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_plan_summary;
import com.example.groupassignment.main;

public class dbmanager_plan_summary {

    private dbhelper_plan_summary dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_plan_summary(Context c) {
        context = c;
    }

    public dbmanager_plan_summary open() throws SQLException {
        dbHelper = new dbhelper_plan_summary(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(String type, int activity_id, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_plan_summary.TYPE, type);
        contentValue.put(dbhelper_plan_summary.ACTIVITY_ID, activity_id);
        contentValue.put(dbhelper_plan_summary.USER_ID, user_id);
        database.insert(dbhelper_plan_summary.TABLE_NAME, null, contentValue);
    }

    // SELECT * FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_plan_summary.TYPE,
                dbhelper_plan_summary.ACTIVITY_ID,
                dbhelper_plan_summary.USER_ID
        };
        Cursor cursor = database.query(dbhelper_plan_summary.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
/*
    //UPDATE * FROM table WHERE plan_summary_id = _id
    public int update(int _id, int hotel_id, int user_id, String airline, int transport_id, int food_id, double total_price, String location) {
        ContentValues contentValues = new ContentValues();
        if(hotel_id!=-1)contentValues.put(dbhelper_plan_summary.HOTEL_ID, hotel_id);
        else if(user_id!=-1)contentValues.put(dbhelper_plan_summary.USER_ID, user_id);
        else if(airline!=null)contentValues.put(dbhelper_plan_summary.AIRLINE, airline);
        else if(transport_id!=-1)contentValues.put(dbhelper_plan_summary.TRANSPORT_ID, transport_id);
        else if(food_id!=-1)contentValues.put(dbhelper_plan_summary.FOOD_ID, food_id);
        else if(total_price!=-1)contentValues.put(dbhelper_plan_summary.TOTAL_PRICE, total_price);
        else if(location!=null)contentValues.put(dbhelper_plan_summary.LOCATION, location);
        int i = database.update(dbhelper_plan_summary.TABLE_NAME, contentValues, dbhelper_plan_summary.PLAN_SUMMARY_ID + " = " + _id, null);
        return i;
    }
*/
    //DELETE * FROM table WHERE plan_summary_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_plan_summary.TABLE_NAME, dbhelper_plan_summary.PLAN_SUMMARY_ID + "=" + _id, null);
    }
}