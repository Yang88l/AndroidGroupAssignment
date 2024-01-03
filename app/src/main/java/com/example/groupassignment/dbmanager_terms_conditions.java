package com.example.groupassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Text;

public class dbmanager_terms_conditions {

    private dbhelper_terms_conditions dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_terms_conditions(Context c) {
        context = c;
    }

    public dbmanager_terms_conditions open() throws SQLException {
        dbHelper = new dbhelper_terms_conditions(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }
    public void insert(String title, String description) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_terms_conditions.TITLE, title);
        contentValue.put(dbhelper_terms_conditions.DESCRIPTION, description);
        database.insert(dbhelper_terms_conditions.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_terms_conditions.TERMS_CONDITIONS_ID,
                dbhelper_terms_conditions.TITLE,
                dbhelper_terms_conditions.DESCRIPTION };
        Cursor cursor = database.query(dbhelper_terms_conditions.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String title, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbhelper_terms_conditions.TITLE, title);
        contentValues.put(dbhelper_terms_conditions.DESCRIPTION, description);
        int i = database.update(dbhelper_terms_conditions.TABLE_NAME, contentValues, dbhelper_terms_conditions.TERMS_CONDITIONS_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_terms_conditions.TABLE_NAME, dbhelper_terms_conditions.TERMS_CONDITIONS_ID + "=" + _id, null);
    }

}
