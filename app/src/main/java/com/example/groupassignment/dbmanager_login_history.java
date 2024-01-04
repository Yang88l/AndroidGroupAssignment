package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_login_history {

    private dbhelper_login_history dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_login_history(Context c) {
        context = c;
    }

    public dbmanager_login_history open() throws SQLException {
        dbHelper = new dbhelper_login_history(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }

    public void insert(int user_id, String status, String activity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_login_history.USER_ID, user_id);
        contentValues.put(dbhelper_login_history.ACTIVITY, activity);
        contentValues.put(dbhelper_login_history.STATUS, status);
        database.insert(dbhelper_login_history.TABLE_NAME, null, contentValues);
    }


    public Cursor fetch() {
        Cursor cursor = database.query(dbhelper_login_history.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE * FROM table WHERE user_id = _id
    public int update(int _id, String activity, String status) {
        ContentValues contentValues = new ContentValues();
        if(activity!=null)contentValues.put(dbhelper_login_history.ACTIVITY, activity);
        else if(status!=null)contentValues.put(dbhelper_login_history.STATUS, status);
        int i = database.update(dbhelper_login_history.TABLE_NAME, contentValues, dbhelper_login_history.LOGIN_ID + " = " + _id, null);
        return i;
    }


    //DELETE * FROM table WHERE hotel_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_login_history.TABLE_NAME, dbhelper_login_history.LOGIN_ID + "=" + _id, null);
    }
}
