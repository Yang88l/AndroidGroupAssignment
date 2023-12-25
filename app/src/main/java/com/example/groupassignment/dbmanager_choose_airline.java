package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_choose_airline {

    private dbhelper_choose_airline dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_choose_airline(Context c) {
        context = c;
    }

    public dbmanager_choose_airline open() throws SQLException {
        dbHelper = new dbhelper_choose_airline(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(int airline_id, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_choose_airline.AIRLINE_ID, airline_id);
        contentValue.put(dbhelper_choose_airline.USER_ID, user_id);
        database.insert(dbhelper_choose_airline.TABLE_NAME, null, contentValue);
    }

    // SELECT airline_id, user_id FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_choose_airline.AIRLINE_ID,
                dbhelper_choose_airline.USER_ID
        };
        Cursor cursor = database.query(dbhelper_choose_airline.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE airline_id, user_id FROM table WHERE choose_airline_id = _id
    public int update(int _id, int airline_id, int user_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_choose_airline.AIRLINE_ID, airline_id);
        contentValues.put(dbhelper_choose_airline.USER_ID, user_id);
        int i = database.update(dbhelper_choose_airline.TABLE_NAME, contentValues, dbhelper_choose_airline.CHOOSE_AIRLINE_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE choose_airline_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_choose_airline.TABLE_NAME, dbhelper_choose_airline.CHOOSE_AIRLINE_ID + "=" + _id, null);
    }
}