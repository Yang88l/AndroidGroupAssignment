package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class dbmanager_favourite {

    private dbhelper_favourite dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_favourite(Context c) {
        context = c;
    }

    public dbmanager_favourite open() throws SQLException {
        dbHelper = new dbhelper_favourite(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(int user_id, String from_where, int from_id, int favourite) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_favourite.USER_ID, user_id);
        contentValue.put(dbhelper_favourite.FROM_WHERE, from_where);
        contentValue.put(dbhelper_favourite.FROM_ID, from_id);
        contentValue.put(dbhelper_favourite.FAVOURITE, favourite);
        database.insert(dbhelper_favourite.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch(int user_id, String from_where, int from_id) {
        String[] columns = new String[] {
                dbhelper_favourite.FAVOURITE_ID,
                dbhelper_favourite.USER_ID,
                dbhelper_favourite.FROM_WHERE,
                dbhelper_favourite.FROM_ID,
                dbhelper_favourite.FAVOURITE
        };
        Cursor cursor = database.query(dbhelper_favourite.TABLE_NAME, columns, dbhelper_favourite.USER_ID + " = " + user_id +" AND "+dbhelper_favourite.FROM_WHERE+" = '"+from_where+"'"+
                " AND "+dbhelper_favourite.FROM_ID+" = "+from_id, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(int user_id, String from_where, int from_id, int favourite) {
        ContentValues contentValues = new ContentValues();
        if(from_where!=null)contentValues.put(dbhelper_favourite.FROM_WHERE, from_where);
        if(from_id!=-1)contentValues.put(dbhelper_favourite.FROM_ID, from_id);
        if(favourite!=-1)contentValues.put(dbhelper_favourite.FAVOURITE, favourite);
        int i = database.update(dbhelper_favourite.TABLE_NAME, contentValues,
                dbhelper_favourite.USER_ID + " = " + user_id +" AND "+dbhelper_favourite.FROM_WHERE+" = '"+from_where+"'"+
                " AND "+dbhelper_favourite.FROM_ID+" = "+from_id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_favourite.TABLE_NAME, dbhelper_favourite.FAVOURITE_ID + "=" + _id, null);
    }

}