package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_choose_transport {

    private dbhelper_choose_transport dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_choose_transport(Context c) {
        context = c;
    }

    public dbmanager_choose_transport open() throws SQLException {
        dbHelper = new dbhelper_choose_transport(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(int transport_id, int user_id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_choose_transport.TRANSPORT_ID, transport_id);
        contentValue.put(dbhelper_choose_transport.USER_ID, user_id);
        database.insert(dbhelper_choose_transport.TABLE_NAME, null, contentValue);
    }

    // SELECT transport_id, user_id FROM table
    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_choose_transport.TRANSPORT_ID,
                dbhelper_choose_transport.USER_ID
        };
        Cursor cursor = database.query(dbhelper_choose_transport.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE transport_id, user_id FROM table WHERE choose_transport_id = _id
    public int update(int _id, int transport_id, int user_id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_choose_transport.TRANSPORT_ID, transport_id);
        contentValues.put(dbhelper_choose_transport.USER_ID, user_id);
        int i = database.update(dbhelper_choose_transport.TABLE_NAME, contentValues, dbhelper_choose_transport.CHOOSE_TRANSPORT_ID + " = " + _id, null);
        return i;
    }

    //DELETE * FROM table WHERE choose_transport_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_choose_transport.TABLE_NAME, dbhelper_choose_transport.CHOOSE_TRANSPORT_ID + "=" + _id, null);
    }
}