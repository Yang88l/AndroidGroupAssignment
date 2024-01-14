package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_hotel_neccessity;
import com.example.groupassignment.main;

public class dbmanager_hotel_neccessity {

    private dbhelper_hotel_neccessity dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_hotel_neccessity(Context c) {
        context = c;
    }

    public dbmanager_hotel_neccessity open() throws SQLException {
        dbHelper = new dbhelper_hotel_neccessity(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(String wifi, String air_condition, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_hotel_neccessity.WIFI, wifi);
        contentValue.put(dbhelper_hotel_neccessity.AIR_CONDITION, air_condition);
        contentValue.put(dbhelper_hotel_neccessity.USER_ID, user_id);
        database.insert(dbhelper_hotel_neccessity.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_hotel_neccessity.HOTEL_NECCESSITY_ID,
                dbhelper_hotel_neccessity.WIFI,
                dbhelper_hotel_neccessity.AIR_CONDITION,
                dbhelper_hotel_neccessity.USER_ID };
        Cursor cursor = database.query(dbhelper_hotel_neccessity.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String wifi, String air_condition, int user_id) {
        ContentValues contentValues = new ContentValues();
        if(wifi!=null)contentValues.put(dbhelper_hotel_neccessity.WIFI, wifi);
        else if(air_condition!=null)contentValues.put(dbhelper_hotel_neccessity.AIR_CONDITION, air_condition);
        else if(user_id!=-1)contentValues.put(dbhelper_hotel_neccessity.USER_ID, user_id);
        int i = database.update(dbhelper_hotel_neccessity.TABLE_NAME, contentValues, dbhelper_hotel_neccessity.HOTEL_NECCESSITY_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_hotel_neccessity.TABLE_NAME, dbhelper_hotel_neccessity.HOTEL_NECCESSITY_ID + "=" + _id, null);
    }

}