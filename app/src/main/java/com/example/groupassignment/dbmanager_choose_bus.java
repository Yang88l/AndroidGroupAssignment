package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_choose_bus {

    private dbhelper_choose_bus dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_choose_bus(Context c) {
        context = c;
    }

    public dbmanager_choose_bus open() throws SQLException {
        dbHelper = new dbhelper_choose_bus(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(int user_id, String state) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_choose_bus.STATE, state);
        contentValue.put(dbhelper_choose_bus.USER_ID, user_id);
        database.insert(dbhelper_choose_bus.TABLE_NAME, null, contentValue);
    }

    // SELECT hotel_id, user_id FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_choose_bus.BUS_ID,
                dbhelper_choose_bus.USER_ID,
                dbhelper_choose_bus.STATE
        };
        Cursor cursor = database.query(dbhelper_choose_bus.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE hotel_id, user_id FROM table WHERE choose_bus_id = _id
    public int update(int _id, int bus_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_choose_bus.BUS_ID, bus_id);
        int i = database.update(dbhelper_choose_bus.TABLE_NAME, contentValues, dbhelper_choose_bus.CHOOSE_BUS_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE choose_bus_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_choose_bus.TABLE_NAME, dbhelper_choose_bus.CHOOSE_BUS_ID + "=" + _id, null);
    }
}