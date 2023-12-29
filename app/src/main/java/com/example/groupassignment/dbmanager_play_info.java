package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_play_info {

    private dbhelper_play_info dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_play_info(Context c) {
        context = c;
    }

    public dbmanager_play_info open() throws SQLException {
        dbHelper = new dbhelper_play_info(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String play_name, double price) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_play_info.PLAY, play_name);
        contentValue.put(dbhelper_play_info.PRICE, price);
        database.insert(dbhelper_play_info.TABLE_NAME, null, contentValue);
    }

    // SELECT play_name, price FROM table
    public Cursor fetch(int _id) {
        String[] columns = new String[] {
                dbhelper_play_info.PLAY_ID,
                dbhelper_play_info.PLAY,
                dbhelper_play_info.PRICE,
                dbhelper_play_info.PICTURE
        };
        Cursor cursor = database.query(dbhelper_play_info.TABLE_NAME, columns, "play_id="+Integer.toString(_id), null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
/*
    public Cursor fetchInnerJoin(int user_id) {
        String query = "SELECT " +
                dbhelper_play_info.PLAY_NAME + ", " +
                dbhelper_airline_info.AIRLINE + ", " +
                dbhelper_food_info.FOOD + ", " +
                dbhelper_transport_info.TRANSPORT + ", " +
                dbhelper_book_summary.TOTAL_PRICE +
                " FROM " + dbhelper_book_summary.TABLE_NAME +
                " INNER JOIN " + dbhelper_play_info.TABLE_NAME + " ON " + dbhelper_book_summary.PLAY_ID + " = " + dbhelper_play_info.PLAY_ID +
                " INNER JOIN " + dbhelper_airline_info.TABLE_NAME + " ON " + dbhelper_book_summary.AIRLINE_ID + " = " + dbhelper_airline_info.AIRLINE_ID +
                " INNER JOIN " + dbhelper_food_info.TABLE_NAME + " ON " + dbhelper_book_summary.FOOD_ID + " = " + dbhelper_food_info.FOOD_ID +
                " INNER JOIN " + dbhelper_transport_info.TABLE_NAME + " ON " + dbhelper_book_summary.TRANSPORT_ID + " = " + dbhelper_transport_info.TRANSPORT_ID +
                " WHERE " + dbhelper_book_summary.USER_ID + " = ?";

        String[] selectionArgs = {String.valueOf(user_id)};

        return database.rawQuery(query, selectionArgs);
    }
*/
    //UPDATE play_name, price FROM table WHERE play_id = _id
    public int update(int _id, String play_name, double price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_play_info.PLAY, play_name);
        contentValues.put(dbhelper_play_info.PRICE, price);
        int i = database.update(dbhelper_play_info.TABLE_NAME, contentValues, dbhelper_play_info.PLAY_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE play_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_play_info.TABLE_NAME, dbhelper_play_info.PLAY_ID + "=" + _id, null);
    }
}