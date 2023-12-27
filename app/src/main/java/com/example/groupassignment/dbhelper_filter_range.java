package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_filter_range extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "filter_range";

    // Table columns
    public static final String FILTER_ID = "filter_id";
    public static final String PRICE = "price";
    public static final String DISTANCE  = "distance";
    public static final String RATING = "rating";
    public static final String AVAILABILITY = "availability";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + FILTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRICE + " DOUBLE NOT NULL, "
            + DISTANCE + " DOUBLE NOT NULL, "
            + RATING + " INTEGER NOT NULL, "
            + AVAILABILITY + " TEXT NOT NULL "
            + ")";

    public dbhelper_filter_range(Context context) {
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