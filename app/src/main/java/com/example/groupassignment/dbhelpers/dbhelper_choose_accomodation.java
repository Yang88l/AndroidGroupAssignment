package com.example.groupassignment.dbhelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_choose_accomodation extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "choose_accomodation";

    // Table columns
    public static final String CHOOSE_ACCOMODATION_ID = "choose_accomodation_id";
    public static final String HOTEL_ID = "hotel_id";
    public static final String USER_ID = "user_id";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + CHOOSE_ACCOMODATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HOTEL_ID + " INTEGER NOT NULL, "
            + USER_ID + " INTEGER NOT NULL"
            + ")";

    public dbhelper_choose_accomodation(Context context) {
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
