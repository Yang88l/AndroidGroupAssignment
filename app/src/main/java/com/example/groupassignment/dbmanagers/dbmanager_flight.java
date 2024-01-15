package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_flight;
import com.example.groupassignment.main;

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
    public void insert(String state, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_flight.TIME, "");
        contentValue.put(dbhelper_flight.FLIGHT_NAME, "");
        contentValue.put(dbhelper_flight.DATE, "");
        contentValue.put(dbhelper_flight.STATE, state);
        contentValue.put(dbhelper_flight.USER_ID, user_id);
        contentValue.put(dbhelper_flight.PAX, 0);
        database.insert(dbhelper_flight.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_flight.FLIGHT_ID,
                dbhelper_flight.TIME,
                dbhelper_flight.FLIGHT_NAME,
                dbhelper_flight.DATE,
                dbhelper_flight.STATE,
                dbhelper_flight.USER_ID,
                dbhelper_flight.PAX
        };
        // WHERE
        //String selection = dbhelper_flight.FLIGHT_ID+"="+_id;

        // Retrieve the data
        Cursor cursor = database.query(dbhelper_flight.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(int _id, String time, String flight_name, String date, String state, int user_id, int pax) {
        ContentValues contentValues = new ContentValues();
        if(time!=null)contentValues.put(dbhelper_flight.TIME, time);
        if(flight_name!=null)contentValues.put(dbhelper_flight.FLIGHT_NAME, flight_name);
        if(date!=null)contentValues.put(dbhelper_flight.DATE, date);
        if(state!=null)contentValues.put(dbhelper_flight.STATE, state);
        if(user_id!=-1)contentValues.put(dbhelper_flight.USER_ID, user_id);
        if(pax!=-1)contentValues.put(dbhelper_flight.PAX, pax);
        int i = database.update(dbhelper_flight.TABLE_NAME, contentValues, dbhelper_flight.FLIGHT_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_flight.TABLE_NAME, dbhelper_flight.FLIGHT_ID + "=" + _id, null);
    }

}
