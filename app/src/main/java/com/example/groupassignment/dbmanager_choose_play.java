package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_choose_play {

    private dbhelper_choose_play dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_choose_play(Context c) {
        context = c;
    }

    public dbmanager_choose_play open() throws SQLException {
        dbHelper = new dbhelper_choose_play(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(int play_id, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_choose_play.PLAY_ID, play_id);
        contentValue.put(dbhelper_choose_play.USER_ID, user_id);
        database.insert(dbhelper_choose_play.TABLE_NAME, null, contentValue);
    }

    // SELECT hotel_id, user_id FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_choose_play.PLAY_ID,
                dbhelper_choose_play.USER_ID
        };
        Cursor cursor = database.query(dbhelper_choose_play.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE hotel_id, user_id FROM table WHERE choose_food_id = _id
    public int update(int _id, int play_id, int user_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_choose_play.PLAY_ID, play_id);
        contentValues.put(dbhelper_choose_play.USER_ID, user_id);
        int i = database.update(dbhelper_choose_play.TABLE_NAME, contentValues, dbhelper_choose_play.CHOOSE_PLAY_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE choose_food_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_choose_play.TABLE_NAME, dbhelper_choose_play.CHOOSE_PLAY_ID + "=" + _id, null);
    }
}