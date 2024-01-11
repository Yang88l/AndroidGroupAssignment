package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_pax;
import com.example.groupassignment.main;

public class dbmanager_pax {

    private dbhelper_pax dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_pax(Context c) {
        context = c;
    }

    public dbmanager_pax open() throws SQLException {
        dbHelper = new dbhelper_pax(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(int user_id, int amount, int adult, int kid) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_pax.USER_ID, user_id);
        contentValue.put(dbhelper_pax.AMOUNT, amount);
        contentValue.put(dbhelper_pax.ADULT, adult);
        contentValue.put(dbhelper_pax.KID, kid);
        database.insert(dbhelper_pax.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_pax.PAX_ID,
                dbhelper_pax.USER_ID,
                dbhelper_pax.AMOUNT,
                dbhelper_pax.ADULT,
                dbhelper_pax.KID };
        Cursor cursor = database.query(dbhelper_pax.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, int user_id, int amount, int adult, int kid) {
        ContentValues contentValues = new ContentValues();
        if(user_id!=-1)contentValues.put(dbhelper_pax.USER_ID, user_id);
        else if(amount!=-1)contentValues.put(dbhelper_pax.AMOUNT, amount);
        else if(adult!=-1)contentValues.put(dbhelper_pax.ADULT, adult);
        else if(kid!=-1)contentValues.put(dbhelper_pax.KID, kid);
        int i = database.update(dbhelper_pax.TABLE_NAME, contentValues, dbhelper_pax.PAX_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_pax.TABLE_NAME, dbhelper_pax.PAX_ID + "=" + _id, null);
    }

}