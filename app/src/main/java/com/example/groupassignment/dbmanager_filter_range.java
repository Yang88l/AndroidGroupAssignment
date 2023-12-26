package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Text;

public class dbmanager_filter_range {

    private dbhelper_filter_range dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_filter_range(Context c) {
        context = c;
    }

    public dbmanager_filter_range open() throws SQLException {
        dbHelper = new dbhelper_filter_range(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(double price, double distance, int rating, String availability, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_filter_range.PRICE, price);
        contentValue.put(dbhelper_filter_range.DISTANCE, distance);
        contentValue.put(dbhelper_filter_range.RATING, rating);
        contentValue.put(dbhelper_filter_range.AVAILABILITY, availability);
        contentValue.put(dbhelper_flight.USER_ID, user_id);
        database.insert(dbhelper_filter_range.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_filter_range.FILTER_ID,
                dbhelper_filter_range.PRICE,
                dbhelper_filter_range.DISTANCE,
                dbhelper_filter_range.RATING,
                dbhelper_filter_range.AVAILABILITY };
        Cursor cursor = database.query(dbhelper_filter_range.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, double price, double distance, int rating, String availability) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_filter_range.PRICE, price);
        contentValues.put(dbhelper_filter_range.DISTANCE, distance);
        contentValues.put(dbhelper_filter_range.RATING, rating);
        contentValues.put(dbhelper_filter_range.AVAILABILITY, availability);
        int i = database.update(dbhelper_filter_range.TABLE_NAME, contentValues, dbhelper_filter_range.FILTER_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_filter_range.TABLE_NAME, dbhelper_filter_range.FILTER_ID + "=" + _id, null);
    }

}
