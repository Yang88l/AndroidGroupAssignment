package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_book_history;
import com.example.groupassignment.dbhelpers.dbhelper_plan_history;
import com.example.groupassignment.main;

public class dbmanager_book_history {

    private dbhelper_book_history dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_book_history(Context c) {
        context = c;
    }

    public dbmanager_book_history open() throws SQLException {
        dbHelper = new dbhelper_book_history(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(int user_id, String activity, String cost) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_plan_history.USER_ID, user_id);
        contentValue.put(dbhelper_plan_history.ACTIVITY, activity);
        contentValue.put(dbhelper_plan_history.COST, cost);
        database.insert(dbhelper_plan_history.TABLE_NAME, null, contentValue);
    }

    // SELECT * FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_plan_history.USER_ID,
                dbhelper_plan_history.ACTIVITY,
                dbhelper_plan_history.COST
        };
        Cursor cursor = database.query(dbhelper_plan_history.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
/*
    //UPDATE * FROM table WHERE book_history_id = _id
    public int update(int _id, int user_id, String location, double cost, String date, String status, int book_summary_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_book_history.USER_ID, user_id);
        contentValues.put(dbhelper_book_history.LOCATION, location);
        contentValues.put(dbhelper_book_history.COST, cost);
        contentValues.put(dbhelper_book_history.DATE, date);
        contentValues.put(dbhelper_book_history.STATUS, status);
        contentValues.put(dbhelper_book_history.BOOK_SUMMARY_ID, book_summary_id);
        int i = database.update(dbhelper_book_history.TABLE_NAME, contentValues, dbhelper_book_history.BOOK_HISTORY_ID + " = " + _id, null);
        return i;
    }*/

    //DELETE * FROM table WHERE book_history_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_book_history.TABLE_NAME, dbhelper_book_history.BOOK_HISTORY_ID + "=" + _id, null);
    }
}