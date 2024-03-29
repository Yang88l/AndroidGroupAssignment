package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_bus;
import com.example.groupassignment.main;

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
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(Integer seat, String bus) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_bus.SEAT, seat);
        contentValue.put(dbhelper_bus.BUS, bus);
        database.insert(dbhelper_bus.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch(int _id) {
        String[] columns = new String[] {
                dbhelper_bus.BUS_ID,
                dbhelper_bus.SEAT,
                dbhelper_bus.BUS };
        Cursor cursor = database.query(dbhelper_bus.TABLE_NAME, columns, dbhelper_bus.BUS_ID+"="+_id, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchALL() {
        String[] columns = new String[] {
                dbhelper_bus.BUS_ID,
                dbhelper_bus.SEAT,
                dbhelper_bus.BUS };
        Cursor cursor = database.query(dbhelper_bus.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(int _id, int seat) {
        ContentValues contentValues = new ContentValues();
        if(seat!=-1)contentValues.put(dbhelper_bus.SEAT, seat);
        int i = database.update(dbhelper_bus.TABLE_NAME, contentValues, dbhelper_bus.BUS_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_bus.TABLE_NAME, dbhelper_bus.BUS_ID + "=" + _id, null);
    }

}
