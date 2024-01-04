package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_choose_food {

    private dbhelper_choose_food dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_choose_food(Context c) {
        context = c;
    }

    public dbmanager_choose_food open() throws SQLException {
        dbHelper = new dbhelper_choose_food(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(int food_id, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_choose_food.FOOD_ID, food_id);
        contentValue.put(dbhelper_choose_food.USER_ID, user_id);
        database.insert(dbhelper_choose_food.TABLE_NAME, null, contentValue);
    }

    // SELECT hotel_id, user_id FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_choose_food.FOOD_ID,
                dbhelper_choose_food.USER_ID
        };
        Cursor cursor = database.query(dbhelper_choose_food.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE hotel_id, user_id FROM table WHERE choose_food_id = _id
    public int update(int _id, int food_id, int user_id) {
        ContentValues contentValues = new ContentValues();
        if(user_id!=-1)contentValues.put(dbhelper_choose_food.FOOD_ID, food_id);
        else if(user_id!=-1)contentValues.put(dbhelper_choose_food.USER_ID, user_id);
        int i = database.update(dbhelper_choose_food.TABLE_NAME, contentValues, dbhelper_choose_food.CHOOSE_FOOD_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE choose_food_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_choose_food.TABLE_NAME, dbhelper_choose_food.CHOOSE_FOOD_ID + "=" + _id, null);
    }
}