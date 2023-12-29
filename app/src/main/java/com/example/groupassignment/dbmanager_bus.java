package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_bus {

    private dbhelper_bus dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_bus(Context c) {
        context = c;
    }

    public dbmanager_bus open() throws SQLException {
        dbHelper = new dbhelper_bus(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Integer seat, String bus, String state) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_bus.SEAT, seat);
        contentValue.put(dbhelper_bus.BUS, bus);
        contentValue.put(dbhelper_bus.STATE, state);
        database.insert(dbhelper_bus.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_bus.BUS_ID,
                dbhelper_bus.SEAT,
                dbhelper_bus.BUS,
                dbhelper_bus.STATE };
        Cursor cursor = database.query(dbhelper_bus.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id,int seat, String bus, String state) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_bus.SEAT, seat);
        contentValues.put(dbhelper_bus.BUS, bus);
        contentValues.put(dbhelper_bus.STATE, state);
        int i = database.update(dbhelper_bus.TABLE_NAME, contentValues, dbhelper_bus.BUS_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_bus.TABLE_NAME, dbhelper_bus.BUS_ID + "=" + _id, null);
    }

}
