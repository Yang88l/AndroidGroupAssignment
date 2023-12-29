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
        dbHelper.close();
    }

    public void insert(int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_login_history.USER_ID, user_id);
        database.insert(dbhelper_login_history.TABLE_NAME, null, contentValue);
    }


    public Cursor fetch(String[] columnsToSelect, int login_id) {
        String[] columns = new String[] {
                dbhelper_login_history.LOGIN_ID,
                dbhelper_login_history.USER_ID
        };
        // WHERE
        String selection = dbhelper_login_history.USER_ID + " = ?";
        // Contains the values for user id
        String[] selectionArgs = { String.valueOf(login_id) };

        // Retrieve the data
        Cursor cursor = database.query(dbhelper_login_history.TABLE_NAME, columnsToSelect, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE * FROM table WHERE user_id = _id
    public int update(int _id, int user_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_login_history.USER_ID, user_id);
        int i = database.update(dbhelper_login_history.TABLE_NAME, contentValues, dbhelper_login_history.LOGIN_ID + " = " + _id, null);
        return i;
    }


    //DELETE * FROM table WHERE hotel_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_login_history.TABLE_NAME, dbhelper_login_history.LOGIN_ID + "=" + _id, null);
    }
}
