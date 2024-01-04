package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_transport_info extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "transport_info";

    // Table columns
    public static final String TRANSPORT_ID = "transport_id";
    public static final String PRICE = "price";
    public static final String TRANSPORT  = "transport";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + TRANSPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRICE + " DOUBLE NOT NULL, "
            + TRANSPORT + " TEXT NOT NULL "
            + ")";

    public dbhelper_transport_info(Context context) {
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