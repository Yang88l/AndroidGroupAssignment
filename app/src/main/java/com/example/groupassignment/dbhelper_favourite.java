package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_favourite extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "favourite";

    // Table columns
    public static final String FAVOURITE_ID = "favouite_id";
    public static final String NAME = "name";
    public static final String REVIEWS = "reviews";
    public static final String TICKET_SOLD = "ticket_sold";
    public static final String PRICE = "price";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + FAVOURITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT NOT NULL, "
            + REVIEWS + " TEXT NOT NULL, "
            + TICKET_SOLD + " INTEGER NOT NULL, "
            + PRICE + " DOUBLE NOT NULL "
            + ")";

    public dbhelper_favourite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}