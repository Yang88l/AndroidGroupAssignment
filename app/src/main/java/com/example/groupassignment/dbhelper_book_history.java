package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_book_history extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "book_history";

    // Table columns
    public static final String BOOK_HISTORY_ID = "book_history_id";
    public static final String USER_ID = "user_id";
    public static final String LOCATION = "location";
    public static final String COST = "cost";
    public static final String DATE = "date";
    public static final String STATUS = "status";
    public static final String BOOK_SUMMARY_ID = "book_summary_id";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + BOOK_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER NOT NULL, "
            + LOCATION + " TEXT NOT NULL, "
            + COST + " DOUBLE NOT NULL, "
            + DATE + " DATE NOT NULL, "
            + STATUS + " TEXT NOT NULL, "
            + BOOK_SUMMARY_ID + " INTEGER NOT NULL, "
            + ")";

    public dbhelper_book_history(Context context) {
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
