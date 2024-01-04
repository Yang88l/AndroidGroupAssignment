package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class dbmanager_notification {

    private dbhelper_notification dbHelper;

    private Context context;


    private SQLiteDatabase database;

    public dbmanager_notification(Context c) {
        context = c;
    }

    public dbmanager_notification open() throws SQLException {
        dbHelper = new dbhelper_notification(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(String content) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_notification.CONTENT, content);
        database.insert(dbhelper_notification.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_notification.NOTIFICATION_ID,
                dbhelper_notification.CONTENT };
        Cursor cursor = database.query(dbhelper_notification.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String content) {
        ContentValues contentValues = new ContentValues();
        if(content!=null)contentValues.put(dbhelper_notification.CONTENT, content);
        int i = database.update(dbhelper_notification.TABLE_NAME, contentValues, dbhelper_notification.NOTIFICATION_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_notification.TABLE_NAME, dbhelper_notification.NOTIFICATION_ID + "=" + _id, null);
    }

}