package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Text;

public class dbmanager_flight {

    private dbhelper_flight dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_flight(Context c) {
        context = c;
    }

    public dbmanager_flight open() throws SQLException {
        dbHelper = new dbhelper_flight(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(String time, String flight_number, String date, String state, int user_id ) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_flight.TIME, time);
        contentValue.put(dbhelper_flight.FLIGHT_NUMBER, flight_number);
        contentValue.put(dbhelper_flight.DATE, date);
        contentValue.put(dbhelper_flight.STATE, state);
        contentValue.put(dbhelper_flight.USER_ID, user_id);
        database.insert(dbhelper_flight.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_flight.FLIGHT_ID,
                dbhelper_flight.TIME,
                dbhelper_flight.FLIGHT_NUMBER,
                dbhelper_flight.DATE,
                dbhelper_flight.STATE,
                dbhelper_flight.USER_ID
        };
        // WHERE
        //String selection = dbhelper_flight.FLIGHT_ID + " = " +_id;

        // Retrieve the data
        Cursor cursor = database.query(dbhelper_flight.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(int _id, String time, String flight_number, String date, String state, int user_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_flight.TIME, time);
        contentValues.put(dbhelper_flight.FLIGHT_NUMBER, flight_number);
        contentValues.put(dbhelper_flight.DATE, date);
        contentValues.put(dbhelper_flight.STATE, state);
        contentValues.put(dbhelper_flight.USER_ID, user_id);
        int i = database.update(dbhelper_flight.TABLE_NAME, contentValues, dbhelper_flight.FLIGHT_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_flight.TABLE_NAME, dbhelper_flight.FLIGHT_ID + "=" + _id, null);
    }

}
