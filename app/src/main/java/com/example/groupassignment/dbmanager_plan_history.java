package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_plan_history {

    private dbhelper_plan_history dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_plan_history(Context c) {
        context = c;
    }

    public dbmanager_plan_history open() throws SQLException {
        dbHelper = new dbhelper_plan_history(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(int user_id, String location, double cost, String date, String status, int plan_summary_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_plan_history.USER_ID, user_id);
        contentValue.put(dbhelper_plan_history.LOCATION, location);
        contentValue.put(dbhelper_plan_history.COST, cost);
        contentValue.put(dbhelper_plan_history.DATE, date);
        contentValue.put(dbhelper_plan_history.STATUS, status);
        contentValue.put(dbhelper_plan_history.PLAN_SUMMARY_ID, plan_summary_id);
        database.insert(dbhelper_plan_history.TABLE_NAME, null, contentValue);
    }

    // SELECT * FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_plan_history.USER_ID,
                dbhelper_plan_history.LOCATION,
                dbhelper_plan_history.COST,
                dbhelper_plan_history.DATE,
                dbhelper_plan_history.STATUS,
                dbhelper_plan_history.PLAN_SUMMARY_ID
        };
        Cursor cursor = database.query(dbhelper_plan_history.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE * FROM table WHERE plan_history_id = _id
    public int update(int _id, int user_id, String location, double cost, String date, String status, int plan_summary_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_plan_history.USER_ID, user_id);
        contentValues.put(dbhelper_plan_history.LOCATION, location);
        contentValues.put(dbhelper_plan_history.COST, cost);
        contentValues.put(dbhelper_plan_history.DATE, date);
        contentValues.put(dbhelper_plan_history.STATUS, status);
        contentValues.put(dbhelper_plan_history.PLAN_SUMMARY_ID, plan_summary_id);
        int i = database.update(dbhelper_plan_history.TABLE_NAME, contentValues, dbhelper_plan_history.PLAN_HISTORY_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE plan_history_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_plan_history.TABLE_NAME, dbhelper_plan_history.PLAN_HISTORY_ID + "=" + _id, null);
    }
}