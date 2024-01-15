package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_accomodation_info;
import com.example.groupassignment.main;

public class dbmanager_accomodation_info {

    private dbhelper_accomodation_info dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_accomodation_info(Context c) {
        context = c;
    }

    public dbmanager_accomodation_info open() throws SQLException {
        dbHelper = new dbhelper_accomodation_info(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(String hotel_name, double price, String picture) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_accomodation_info.HOTEL_NAME, hotel_name);
        contentValue.put(dbhelper_accomodation_info.PRICE, price);
        contentValue.put(dbhelper_accomodation_info.PICTURE, picture);
        database.insert(dbhelper_accomodation_info.TABLE_NAME, null, contentValue);
    }

    // SELECT hotel_name, price FROM table
    public Cursor fetch(int _id) {
        String[] columns = new String[] {
                dbhelper_accomodation_info.HOTEL_ID,
                dbhelper_accomodation_info.HOTEL_NAME,
                dbhelper_accomodation_info.PRICE,
                dbhelper_accomodation_info.PICTURE
        };
        Cursor cursor = database.query(dbhelper_accomodation_info.TABLE_NAME, columns, "hotel_id="+Integer.toString(_id), null, null, null, null);
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
*/

    //UPDATE hotel_name, price FROM table WHERE hotel_id = _id
    public int update(int _id, String hotel_name, double price) {
        ContentValues contentValues = new ContentValues();
        if(hotel_name!=null) contentValues.put(dbhelper_accomodation_info.HOTEL_NAME, hotel_name);
        else if(price!=-1) contentValues.put(dbhelper_accomodation_info.PRICE, price);
        int i = database.update(dbhelper_accomodation_info.TABLE_NAME, contentValues, dbhelper_accomodation_info.HOTEL_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE hotel_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_accomodation_info.TABLE_NAME, dbhelper_accomodation_info.HOTEL_ID + "=" + _id, null);
    }
}