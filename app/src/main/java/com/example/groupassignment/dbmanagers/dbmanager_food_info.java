package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_food_info;
import com.example.groupassignment.main;

public class dbmanager_food_info {

    private dbhelper_food_info dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_food_info(Context c) {
        context = c;
    }

    public dbmanager_food_info open() throws SQLException {
        dbHelper = new dbhelper_food_info(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(String food, double price, String picture) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_food_info.PRICE, price);
        contentValue.put(dbhelper_food_info.FOOD, food);
        contentValue.put(dbhelper_food_info.PICTURE, picture);
        database.insert(dbhelper_food_info.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch(int _id) {
        String[] columns = new String[] {
                dbhelper_food_info.FOOD_ID,
                dbhelper_food_info.FOOD,
                dbhelper_food_info.PRICE,
                dbhelper_food_info.PICTURE
        };
        Cursor cursor = database.query(dbhelper_food_info.TABLE_NAME, columns, "food_id="+Integer.toString(_id), null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, double price, String food) {
        ContentValues contentValues = new ContentValues();
        if(price!=-1)contentValues.put(dbhelper_food_info.PRICE, price);
        else if(food!=null)contentValues.put(dbhelper_food_info.FOOD, food);
        int i = database.update(dbhelper_food_info.TABLE_NAME, contentValues, dbhelper_food_info.FOOD_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_food_info.TABLE_NAME, dbhelper_food_info.FOOD_ID + "=" + _id, null);
    }

}
