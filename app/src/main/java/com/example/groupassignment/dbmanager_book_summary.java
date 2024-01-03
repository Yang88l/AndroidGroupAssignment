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
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(String type, int activity_id, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_book_summary.TYPE, type);
        contentValue.put(dbhelper_book_summary.ACTIVITY_ID, activity_id);
        contentValue.put(dbhelper_book_summary.USER_ID, user_id);
        database.insert(dbhelper_book_summary.TABLE_NAME, null, contentValue);
    }

    // SELECT * FROM table
    public Cursor fetch() {
        Cursor cursor = database.query(dbhelper_book_summary.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
/*
    public Cursor fetchInnerJoin(int user_id) {
        String query = "SELECT " +
                dbhelper_accomodation_info.HOTEL_NAME + ", " +
                dbhelper_airline_info.AIRLINE + ", " +
                dbhelper_food_info.FOOD + ", " +
                dbhelper_transport_info.TRANSPORT + ", " +
                dbhelper_book_summary.TOTAL_PRICE +
                " FROM " + dbhelper_book_summary.TABLE_NAME +
                " INNER JOIN " + dbhelper_accomodation_info.TABLE_NAME + " ON " + dbhelper_book_summary.HOTEL_ID + " = " + dbhelper_accomodation_info.HOTEL_ID +
                " INNER JOIN " + dbhelper_airline_info.TABLE_NAME + " ON " + dbhelper_book_summary.AIRLINE_ID + " = " + dbhelper_airline_info.AIRLINE_ID +
                " INNER JOIN " + dbhelper_food_info.TABLE_NAME + " ON " + dbhelper_book_summary.FOOD_ID + " = " + dbhelper_food_info.FOOD_ID +
                " INNER JOIN " + dbhelper_transport_info.TABLE_NAME + " ON " + dbhelper_book_summary.TRANSPORT_ID + " = " + dbhelper_transport_info.TRANSPORT_ID +
                " WHERE " + dbhelper_book_summary.USER_ID + " = ?";

        String[] selectionArgs = {String.valueOf(user_id)};

        return database.rawQuery(query, selectionArgs);
    }

    //UPDATE * FROM table WHERE book_summary_id = _id
    public int update(int _id, int hotel_id, int user_id, String airline, int transport_id, int food_id, double total_price, String location) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_book_summary.HOTEL_ID, hotel_id);
        contentValues.put(dbhelper_book_summary.USER_ID, user_id);
        contentValues.put(dbhelper_book_summary.AIRLINE_ID, airline);
        contentValues.put(dbhelper_book_summary.TRANSPORT_ID, transport_id);
        contentValues.put(dbhelper_book_summary.FOOD_ID, food_id);
        contentValues.put(dbhelper_book_summary.TOTAL_PRICE, total_price);
        contentValues.put(dbhelper_book_summary.LOCATION, location);
        int i = database.update(dbhelper_book_summary.TABLE_NAME, contentValues, dbhelper_book_summary.BOOK_SUMMARY_ID + " = " + _id, null);
        return i;
    }
*/
    //DELETE * FROM table WHERE book_summary_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_book_summary.TABLE_NAME, dbhelper_book_summary.BOOK_SUMMARY_ID + "=" + _id, null);
    }
}