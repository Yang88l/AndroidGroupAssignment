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
    public void insert(String name, String reviews, int ticket_sold, double price) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbhelper_favourite.NAME, name);
        contentValue.put(dbhelper_favourite.REVIEWS, reviews);
        contentValue.put(dbhelper_favourite.TICKET_SOLD, ticket_sold);
        contentValue.put(dbhelper_favourite.PRICE, price);
        database.insert(dbhelper_favourite.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] {
                dbhelper_favourite.FAVOURITE_ID,
                dbhelper_favourite.NAME,
                dbhelper_favourite.REVIEWS,
                dbhelper_favourite.TICKET_SOLD,
                dbhelper_favourite.PRICE,};
        Cursor cursor = database.query(dbhelper_favourite.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String reviews, int ticket_sold, double price) {
        ContentValues contentValues = new ContentValues();
        if(name!=null)contentValues.put(dbhelper_favourite.NAME, name);
        else if(reviews!=null)contentValues.put(dbhelper_favourite.REVIEWS, reviews);
        else if(ticket_sold!=-1)contentValues.put(dbhelper_favourite.TICKET_SOLD, ticket_sold);
        else if(price!=-1)contentValues.put(dbhelper_favourite.PRICE, price);
        int i = database.update(dbhelper_favourite.TABLE_NAME, contentValues, dbhelper_favourite.FAVOURITE_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbhelper_favourite.TABLE_NAME, dbhelper_favourite.FAVOURITE_ID + "=" + _id, null);
    }

}