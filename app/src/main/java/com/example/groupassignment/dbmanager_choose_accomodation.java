package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_choose_accomodation {

    private dbhelper_choose_accomodation dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_choose_accomodation(Context c) {
        context = c;
    }

    public dbmanager_choose_accomodation open() throws SQLException {
        dbHelper = new dbhelper_choose_accomodation(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(int hotel_id, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_choose_accomodation.HOTEL_ID, hotel_id);
        contentValue.put(dbhelper_choose_accomodation.USER_ID, user_id);
        database.insert(dbhelper_choose_accomodation.TABLE_NAME, null, contentValue);
    }

    // SELECT hotel_id, user_id FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_choose_accomodation.HOTEL_ID,
                dbhelper_choose_accomodation.USER_ID
        };
        Cursor cursor = database.query(dbhelper_choose_accomodation.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchInnerJoin(int user_id) {
        String query = "SELECT " +
                dbhelper_accomodation_info.HOTEL_NAME + ", " +
                dbhelper_airline_info.AIRLINE + ", " +
                dbhelper_food_info.FOOD + ", " +
                dbhelper_transport_info.TRANSPORT + ", " +
                dbhelper_book_summary.TOTAL_PRICE +
                " FROM " + dbhelper_accomodation_info.TABLE_NAME +
                " INNER JOIN " + dbhelper_airline_info.TABLE_NAME + " ON " + dbhelper_book_summary.AIRLINE_ID + " = " + dbhelper_airline_info.AIRLINE_ID +
                " INNER JOIN " + dbhelper_food_info.TABLE_NAME + " ON " + dbhelper_book_summary.FOOD_ID + " = " + dbhelper_food_info.FOOD_ID +
                " INNER JOIN " + dbhelper_transport_info.TABLE_NAME + " ON " + dbhelper_book_summary.TRANSPORT_ID + " = " + dbhelper_transport_info.TRANSPORT_ID +
                " WHERE " + dbhelper_book_summary.USER_ID + " = ?";

        String[] selectionArgs = {String.valueOf(user_id)};

        return database.rawQuery(query, selectionArgs);
    }

    //UPDATE hotel_id, user_id FROM table WHERE choose_accomodation_id = _id
    public int update(int _id, int hotel_id, int user_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_choose_accomodation.HOTEL_ID, hotel_id);
        contentValues.put(dbhelper_choose_accomodation.USER_ID, user_id);
        int i = database.update(dbhelper_choose_accomodation.TABLE_NAME, contentValues, dbhelper_choose_accomodation.CHOOSE_ACCOMODATION_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE choose_accomodation_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_choose_accomodation.TABLE_NAME, dbhelper_choose_accomodation.CHOOSE_ACCOMODATION_ID + "=" + _id, null);
    }
}