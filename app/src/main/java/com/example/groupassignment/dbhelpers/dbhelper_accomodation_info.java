package com.example.groupassignment.dbhelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_accomodation_info extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "accomodation_info";
    // Table columns
    public static final String HOTEL_ID = "hotel_id";
    public static final String HOTEL_NAME = "hotel_name";
    public static final String PRICE = "price";
    public static final String PICTURE = "picture";
    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + HOTEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HOTEL_NAME + " TEXT NOT NULL, "
            + PRICE + " DOUBLE NOT NULL, "
            + PICTURE + " TEXT NOT NULL "
            + ")";

    public dbhelper_accomodation_info(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
