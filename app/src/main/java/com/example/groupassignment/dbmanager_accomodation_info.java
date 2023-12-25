package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
        dbHelper.close();
    }

    public void insert(int hotel_name, double price) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_accomodation_info.HOTEL_NAME, hotel_name);
        contentValue.put(dbhelper_accomodation_info.PRICE, price);
        database.insert(dbhelper_accomodation_info.TABLE_NAME, null, contentValue);
    }

    // SELECT hotel_name, price FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_accomodation_info.HOTEL_NAME,
                dbhelper_accomodation_info.PRICE
        };
        Cursor cursor = database.query(dbhelper_accomodation_info.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE hotel_name, price FROM table WHERE hotel_id = _id
    public int update(int _id, String hotel_name, double price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_accomodation_info.HOTEL_NAME, hotel_name);
        contentValues.put(dbhelper_accomodation_info.PRICE, price);
        int i = database.update(dbhelper_accomodation_info.TABLE_NAME, contentValues, dbhelper_accomodation_info.HOTEL_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE hotel_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_accomodation_info.TABLE_NAME, dbhelper_accomodation_info.HOTEL_ID + "=" + _id, null);
    }
}