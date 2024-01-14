package com.example.groupassignment.dbhelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_flight extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "flight";

    // Table columns
    public static final String FLIGHT_ID = "flight_id";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String FLIGHT_NUMBER  = "flight_number";
    public static final String DEPARTURE_TIME = "departure_time";
    public static final String STATE = "state";
    public static final String USER_ID = "user_id";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + FLIGHT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TIME + " TEXT NOT NULL, "
            + DATE + " TEXT NOT NULL, "
            + FLIGHT_NUMBER + " TEXT NOT NULL, "
            + DEPARTURE_TIME + " TEXT NOT NULL, "
            + STATE + " TEXT NOT NULL, "
            + USER_ID + " INTEGER NOT NULL"
            + ")";

    public dbhelper_flight(Context context) {
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
