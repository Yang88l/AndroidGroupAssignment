package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_plan_history;
import com.example.groupassignment.dbhelpers.dbhelper_plan_summary;
import com.example.groupassignment.main;

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
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(int user_id, int login_id,  String activity, String cost) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_plan_history.USER_ID, user_id);
        contentValue.put(dbhelper_plan_history.LOGIN_ID, login_id);
        contentValue.put(dbhelper_plan_history.ACTIVITY, activity);
        contentValue.put(dbhelper_plan_history.COST, cost);
        database.insert(dbhelper_plan_history.TABLE_NAME, null, contentValue);
    }

    // SELECT * FROM table
    public Cursor fetch(int user_id) {
        String[] columns = new String[] {
                dbhelper_plan_history.USER_ID,
                dbhelper_plan_history.LOGIN_ID,
                dbhelper_plan_history.ACTIVITY,
                dbhelper_plan_history.COST
        };
        Cursor cursor = database.query(dbhelper_plan_history.TABLE_NAME, columns, dbhelper_plan_history.USER_ID+"="+user_id, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE * FROM table WHERE plan_history_id = _id
    public int update(int user_id, int login_id, String activity, String cost) {
        ContentValues contentValues = new ContentValues();
        if(activity!=null)contentValues.put(dbhelper_plan_history.ACTIVITY, activity);
        if(cost!=null)contentValues.put(dbhelper_plan_history.COST, cost);
        int i = database.update(dbhelper_plan_history.TABLE_NAME, contentValues, dbhelper_plan_history.USER_ID + " = " + user_id +" AND "+dbhelper_plan_history.LOGIN_ID + " = " + login_id, null);
        return i;
    }

    //DELETE * FROM table WHERE plan_history_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_plan_history.TABLE_NAME, dbhelper_plan_history.PLAN_HISTORY_ID + "=" + _id, null);
    }
}