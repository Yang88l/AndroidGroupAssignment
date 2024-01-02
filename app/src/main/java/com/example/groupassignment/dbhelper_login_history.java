package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_login_history extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "login_history";

    // Table columns
    public static final String LOGIN_ID = "login_id";
    public static final String USER_ID = "user_id";
    public static final String ACTIVITY = "activity";
    public static final String STATUS = "status";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + LOGIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER NOT NULL, "
            + ACTIVITY + " TEXT NOT NULL, "
            + STATUS + " TEXT NOT NULL "
            + ")";

    public dbhelper_login_history(Context context) {
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


