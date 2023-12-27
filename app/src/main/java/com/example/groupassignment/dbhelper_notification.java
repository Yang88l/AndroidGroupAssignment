package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_notification extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "notification";

    // Table columns
    public static final String NOTIFICATION_ID = "notification_id";
    public static final String CONTENT = "content";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + NOTIFICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CONTENT + " TEXT NOT NULL "
            + ")";

    public dbhelper_notification(Context context) {
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