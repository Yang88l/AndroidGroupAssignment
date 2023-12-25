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