package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_book_summary {

    private dbhelper_book_summary dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_book_summary(Context c) {
        context = c;
    }

    public dbmanager_book_summary open() throws SQLException {
        dbHelper = new dbhelper_book_summary(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(int hotel_id, int user_id, String airline, int transport_id, int food_id, double total_price, String location) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_book_summary.HOTEL_ID, hotel_id);
        contentValue.put(dbhelper_book_summary.USER_ID, user_id);
        contentValue.put(dbhelper_book_summary.AIRLINE, airline);
        contentValue.put(dbhelper_book_summary.TRANSPORT, transport_id);
        contentValue.put(dbhelper_book_summary.FOOD_ID, food_id);
        contentValue.put(dbhelper_book_summary.TOTAL_PRICE, total_price);
        contentValue.put(dbhelper_book_summary.LOCATION, location);
        database.insert(dbhelper_book_summary.TABLE_NAME, null, contentValue);
    }

    // SELECT * FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_book_summary.HOTEL_ID,
                dbhelper_book_summary.USER_ID,
                dbhelper_book_summary.AIRLINE,
                dbhelper_book_summary.TRANSPORT,
                dbhelper_book_summary.FOOD_ID,
                dbhelper_book_summary.TOTAL_PRICE,
                dbhelper_book_summary.LOCATION
        };
        Cursor cursor = database.query(dbhelper_book_summary.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE * FROM table WHERE book_summary_id = _id
    public int update(int _id, int hotel_id, int user_id, String airline, int transport_id, int food_id, double total_price, String location) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_book_summary.HOTEL_ID, hotel_id);
        contentValues.put(dbhelper_book_summary.USER_ID, user_id);
        contentValues.put(dbhelper_book_summary.AIRLINE, airline);
        contentValues.put(dbhelper_book_summary.TRANSPORT, transport_id);
        contentValues.put(dbhelper_book_summary.FOOD_ID, food_id);
        contentValues.put(dbhelper_book_summary.TOTAL_PRICE, total_price);
        contentValues.put(dbhelper_book_summary.LOCATION, location);
        int i = database.update(dbhelper_book_summary.TABLE_NAME, contentValues, dbhelper_book_summary.BOOK_SUMMARY_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE book_summary_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_book_summary.TABLE_NAME, dbhelper_book_summary.BOOK_SUMMARY_ID + "=" + _id, null);
    }
}