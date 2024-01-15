package com.example.groupassignment.dbhelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_airline_info extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "airline_info";

    // Table columns
    public static final String AIRLINE_ID = "airline_id";
    public static final String AIRLINE = "airline";
    public static final String PRICE = "price";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + AIRLINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AIRLINE + " TEXT NOT NULL, "
            + PRICE + " DOUBLE NOT NULL "
            + ")";

    public dbhelper_airline_info(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL("INSERT INTO airline_info (AIRLINE, PRICE) VALUES ('Air Asia', 100), ('Firefly', 100), ('Berjaya Air', 100);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
