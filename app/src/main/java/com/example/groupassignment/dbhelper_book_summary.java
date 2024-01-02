package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_book_summary extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "book_summary";

    // Table columns
    public static final String BOOK_SUMMARY_ID = "book_summary_id";
    public static final String TYPE = "type";
    public static final String ACTIVITY_ID = "activity_id";
    public static final String USER_ID = "user_id";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = main.dbversion++;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + BOOK_SUMMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TYPE + " TEXT NOT NULL, "
            + ACTIVITY_ID + " INTEGER NOT NULL, "
            + USER_ID + " INTEGER NOT NULL "
            + ")";

    public dbhelper_book_summary(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
       // db.execSQL("INSERT INTO booking_summary (USER_ID, AIRLINE_ID, TRANSPORT_ID, FOOD_ID, TOTAL_PRICE, LOCATION) VALUES (1,1,1,1,10,'KAJANG');");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
