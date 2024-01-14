package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_train;
import com.example.groupassignment.main;

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
    public void insert(int user_id, String location_name, String from_where, String to_where, String time, Double cost) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_train.USER_ID, user_id);
        contentValue.put(dbhelper_train.LOCATION_NAME, location_name);
        contentValue.put(dbhelper_train.FROM_WHERE, from_where);
        contentValue.put(dbhelper_train.TO_WHERE, to_where);
        contentValue.put(dbhelper_train.TIME, time);
        contentValue.put(dbhelper_train.COST, cost);
        database.insert(dbhelper_train.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{
                dbhelper_train.TRAIN_ID,
                dbhelper_train.USER_ID,
                dbhelper_train.LOCATION_NAME,
                dbhelper_train.FROM_WHERE,
                dbhelper_train.TO_WHERE,
                dbhelper_train.TIME,
                dbhelper_train.COST
                };
        // WHERE
        //String selection = dbhelper_train.LOCATION_NAME + " = ?";
        //contains the location value
        //String[] selectionArgs = { String.valueOf(location_name) };

        // Retrieve the data
        Cursor cursor = database.query(dbhelper_train.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, int user_id, String location_name, String from_where, String to_where, String time, Double cost) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_train.LOCATION_NAME, user_id);
        contentValues.put(dbhelper_train.LOCATION_NAME, location_name);
        contentValues.put(dbhelper_train.LOCATION_NAME, from_where);
        contentValues.put(dbhelper_train.LOCATION_NAME, to_where);
        contentValues.put(dbhelper_train.LOCATION_NAME, time);
        contentValues.put(dbhelper_train.LOCATION_NAME, cost);
        int i = database.update(dbhelper_train.TABLE_NAME, contentValues, dbhelper_train.TRAIN_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_train.TABLE_NAME, dbhelper_train.TRAIN_ID + "=" + _id, null);
    }

}