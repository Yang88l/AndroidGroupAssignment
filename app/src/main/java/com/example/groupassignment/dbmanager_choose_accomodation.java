package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbmanager_choose_accomodation {

    private dbhelper_choose_accomodation dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_choose_accomodation(Context c) {
        context = c;
    }

    public dbmanager_choose_accomodation open() throws SQLException {
        dbHelper = new dbhelper_choose_accomodation(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }
/*
    public void insert(String income, String category1, String percentage1, String category2,
                       String percentage2,String category3, String percentage3) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_choose_accomodation.INCOME, income);
        contentValue.put(dbhelper_choose_accomodation.CATEGORY1, category1);
        contentValue.put(dbhelper_choose_accomodation.PERCENTAGE1, percentage1);
        contentValue.put(dbhelper_choose_accomodation.CATEGORY2, category2);
        contentValue.put(dbhelper_choose_accomodation.PERCENTAGE2, percentage2);
        contentValue.put(dbhelper_choose_accomodation.CATEGORY3, category3);
        contentValue.put(dbhelper_choose_accomodation.PERCENTAGE3, percentage3);
        database.insert(dbhelper_choose_accomodation.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { dbhelper_choose_accomodation._ID, dbhelper_choose_accomodation.INCOME, dbhelper_choose_accomodation.CATEGORY1, dbhelper_choose_accomodation.PERCENTAGE1, dbhelper_choose_accomodation.CATEGORY2, dbhelper_choose_accomodation.PERCENTAGE2, dbhelper_choose_accomodation.CATEGORY3, dbhelper_choose_accomodation.PERCENTAGE3 };
        Cursor cursor = database.query(dbhelper_choose_accomodation.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public Cursor fetch(string _id) { //pass parameter
        String[] columns = new String[] { dbhelper_choose_accomodation._ID, dbhelper_choose_accomodation.SUBJECT, dbhelper_choose_accomodation.DESC };
        Cursor cursor = database.query(dbhelper_choose_accomodation.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public int update(long _id, String income, String category1, String percentage1, String category2, String percentage2, String category3, String percentage3) {
        ContentValues contentValues = new ContentValues();
        if (income!=null) contentValues.put(dbhelper_choose_accomodation.INCOME, income);
        if (category1!=null) contentValues.put(dbhelper_choose_accomodation.CATEGORY1, category1);
        if (percentage1!=null) contentValues.put(dbhelper_choose_accomodation.PERCENTAGE1, percentage1);
        if (category2!=null) contentValues.put(dbhelper_choose_accomodation.CATEGORY2, category2);
        if (percentage2!=null) contentValues.put(dbhelper_choose_accomodation.PERCENTAGE2, percentage2);
        if (category3!=null) contentValues.put(dbhelper_choose_accomodation.CATEGORY3, category3);
        if (percentage3!=null) contentValues.put(dbhelper_choose_accomodation.PERCENTAGE3, percentage3);
        int i = database.update(dbhelper_choose_accomodation.TABLE_NAME, contentValues, dbhelper_choose_accomodation._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_choose_accomodation.TABLE_NAME, dbhelper_choose_accomodation._ID + "=" + _id, null);
    }
*/
}