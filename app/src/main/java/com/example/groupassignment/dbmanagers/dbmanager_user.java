package com.example.groupassignment.dbmanagers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.groupassignment.dbhelpers.dbhelper_user;
import com.example.groupassignment.main;

public class dbmanager_user {

    private dbhelper_user dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public dbmanager_user(Context c) {
        context = c;
    }

    public dbmanager_user open() throws SQLException {
        dbHelper = new dbhelper_user(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        main.saveVersion(context);
        dbHelper.close();
    }

    public void insert(String name, String email, String phone, String birthday, String password, String picture) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_user.NAME, name);
        contentValue.put(dbhelper_user.EMAIL, email);
        contentValue.put(dbhelper_user.PHONE, phone);
        contentValue.put(dbhelper_user.BIRTHDAY, birthday);
        contentValue.put(dbhelper_user.PASSWORD, password);
        contentValue.put(dbhelper_user.PICTURE, picture);
        database.insert(dbhelper_user.TABLE_NAME, null, contentValue);
    }

    // SELECT * FROM table
    public Cursor fetchALL() {
        String[] columns = new String[] {
                dbhelper_user.USER_ID,
                dbhelper_user.NAME,
                dbhelper_user.EMAIL,
                dbhelper_user.PHONE,
                dbhelper_user.BIRTHDAY,
                dbhelper_user.PASSWORD,
                dbhelper_user.PICTURE
        };
        // WHERE
        //String selection = dbhelper_user.NAME + " = ? AND" + dbhelper_user.PASSWORD + " = ?";
        // Contains the values for name and password
        //String[] selectionArgs = { dbhelper_user.NAME, dbhelper_user.PASSWORD };

        // Retrieve the data
        Cursor cursor = database.query(dbhelper_user.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetch(int user_id) {
        String[] columns = new String[] {
                dbhelper_user.USER_ID,
                dbhelper_user.NAME,
                dbhelper_user.EMAIL,
                dbhelper_user.PHONE,
                dbhelper_user.BIRTHDAY,
                dbhelper_user.PASSWORD,
                dbhelper_user.PICTURE
        };
        Cursor cursor = database.query(dbhelper_user.TABLE_NAME, columns, dbhelper_user.USER_ID+"="+user_id, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchByName(String name) {
        String[] columns = new String[] {
                dbhelper_user.USER_ID,
                dbhelper_user.NAME,
                dbhelper_user.EMAIL,
                dbhelper_user.PHONE,
                dbhelper_user.BIRTHDAY,
                dbhelper_user.PASSWORD,
                dbhelper_user.PICTURE
        };
        Cursor cursor = database.query(dbhelper_user.TABLE_NAME, columns, dbhelper_user.NAME+"= '"+name+"'", null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //UPDATE * FROM table WHERE user_id = _id
    public int update(int _id, String name, String email, String phone, String birthday, String password, String picture) {
        ContentValues contentValues = new ContentValues();
        if(name!=null) contentValues.put(dbhelper_user.NAME, name);
        if(email!=null) contentValues.put(dbhelper_user.EMAIL, email);
        if(phone!=null) contentValues.put(dbhelper_user.PHONE, phone);
        if(birthday!=null) contentValues.put(dbhelper_user.BIRTHDAY, birthday);
        if(password!=null) contentValues.put(dbhelper_user.PASSWORD, password);
        if(picture!=null) contentValues.put(dbhelper_user.PICTURE, picture);
        int i = database.update(dbhelper_user.TABLE_NAME, contentValues, dbhelper_user.USER_ID + " = " + _id, null);
        return i;
    }


    //DELETE * FROM table WHERE hotel_id = _id
    public void delete(long _id) {
        database.delete(dbhelper_user.TABLE_NAME, dbhelper_user.USER_ID + "=" + _id, null);
    }
}