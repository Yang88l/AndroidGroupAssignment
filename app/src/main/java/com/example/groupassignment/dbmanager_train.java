package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_train {

    private dbhelper_train dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_train(Context c) {
        context = c;
    }

    public dbmanager_train open() throws SQLException {
        dbHelper = new dbhelper_train(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(String location_name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_train.LOCATION_NAME, location_name);
        database.insert(dbhelper_train.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch(String[] location_name) {
        String[] columns = new String[]{
                dbhelper_train.TRAIN_ID,
                dbhelper_train.LOCATION_NAME
                };
        // WHERE
        String selection = dbhelper_train.LOCATION_NAME + " = ?";
        //contains the location value
        String[] selectionArgs = { String.valueOf(location_name) };

        // Retrieve the data
        Cursor cursor = database.query(dbhelper_train.TABLE_NAME, columns, selection, location_name, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String location_name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_train.LOCATION_NAME, location_name);
        int i = database.update(dbhelper_train.TABLE_NAME, contentValues, dbhelper_train.TRAIN_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_train.TABLE_NAME, dbhelper_train.TRAIN_ID + "=" + _id, null);
    }

}