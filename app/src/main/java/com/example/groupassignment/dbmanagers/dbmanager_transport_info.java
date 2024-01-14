package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_transport_info;
import com.example.groupassignment.main;

public class dbmanager_transport_info {

    private dbhelper_transport_info dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_transport_info(Context c) {
        context = c;
    }

    public dbmanager_transport_info open() throws SQLException {
        dbHelper = new dbhelper_transport_info(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }

    public void insert(double price, String food) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_transport_info.PRICE, price);
        contentValue.put(dbhelper_transport_info.TRANSPORT, food);
        database.insert(dbhelper_transport_info.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_transport_info.TRANSPORT_ID,
                dbhelper_transport_info.TRANSPORT,
                dbhelper_transport_info.PRICE };
        Cursor cursor = database.query(dbhelper_transport_info.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, double price, String transport) {
        ContentValues contentValues = new ContentValues();
        if(price!=-1)contentValues.put(dbhelper_transport_info.PRICE, price);
        else if(transport!=null)contentValues.put(dbhelper_transport_info.TRANSPORT, transport);
        int i = database.update(dbhelper_transport_info.TABLE_NAME, contentValues, dbhelper_transport_info.TRANSPORT_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_transport_info.TABLE_NAME, dbhelper_transport_info.TRANSPORT_ID + "=" + _id, null);
    }

}
