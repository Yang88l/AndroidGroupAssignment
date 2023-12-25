package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Text;

public class dbmanager_message {

    private dbhelper_message dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_message(Context c) {
        context = c;
    }

    public dbmanager_message open() throws SQLException {
        dbHelper = new dbhelper_message(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String message, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_message.NAME, name);
        contentValue.put(dbhelper_message.MESSAGE, message);
        contentValue.put(dbhelper_message.USER_ID, user_id);
        database.insert(dbhelper_message.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_message.MESSAGE_ID,
                dbhelper_message.NAME,
                dbhelper_message.MESSAGE,
                dbhelper_message.USER_ID };
        Cursor cursor = database.query(dbhelper_message.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String message, int user_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_message.NAME, name);
        contentValues.put(dbhelper_message.MESSAGE, message);
        contentValues.put(dbhelper_message.USER_ID, user_id);
        int i = database.update(dbhelper_message.TABLE_NAME, contentValues, dbhelper_message.MESSAGE_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_message.TABLE_NAME, dbhelper_message.MESSAGE_ID + "=" + _id, null);
    }

}