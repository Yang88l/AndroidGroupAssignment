package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_book_history;
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
    public void insert(int user_id, int login_id,  String activity, String cost) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_book_history.USER_ID, user_id);
        contentValue.put(dbhelper_book_history.LOGIN_ID, login_id);
        contentValue.put(dbhelper_book_history.ACTIVITY, activity);
        contentValue.put(dbhelper_book_history.COST, cost);
        contentValue.put(dbhelper_book_history.PRICE, 0);
        database.insert(dbhelper_book_history.TABLE_NAME, null, contentValue);
    }

    // SELECT * FROM table
    public Cursor fetch(int user_id) {
        String[] columns = new String[] {
                dbhelper_book_history.USER_ID,
                dbhelper_book_history.LOGIN_ID,
                dbhelper_book_history.ACTIVITY,
                dbhelper_book_history.COST,
                dbhelper_book_history.PRICE
        };
        Cursor cursor = database.query(dbhelper_book_history.TABLE_NAME, columns, dbhelper_book_history.USER_ID+"="+user_id, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchWithLoginID(int user_id, int login_id) {
        String[] columns = new String[] {
                dbhelper_book_history.USER_ID,
                dbhelper_book_history.LOGIN_ID,
                dbhelper_book_history.ACTIVITY,
                dbhelper_book_history.COST,
                dbhelper_book_history.PRICE
        };
        Cursor cursor = database.query(dbhelper_book_history.TABLE_NAME, columns, dbhelper_book_history.USER_ID+"="+user_id+" AND "+dbhelper_book_history.LOGIN_ID+"="+login_id, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE * FROM table WHERE book_history_id = _id
    public int update(int user_id, int login_id, String activity, String cost, double price) {
        ContentValues contentValues = new ContentValues();
        if(activity!=null)contentValues.put(dbhelper_book_history.ACTIVITY, activity);
        if(cost!=null)contentValues.put(dbhelper_book_history.COST, cost);
        if(price!=-1)contentValues.put(dbhelper_book_history.PRICE, price);
        int i = database.update(dbhelper_book_history.TABLE_NAME, contentValues, dbhelper_book_history.USER_ID + " = " + user_id +" AND "+dbhelper_book_history.LOGIN_ID + " = " + login_id, null);
        return i;
    }

    //DELETE * FROM table WHERE book_history_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_book_history.TABLE_NAME, dbhelper_book_history.BOOK_HISTORY_ID + "=" + _id, null);
    }
}