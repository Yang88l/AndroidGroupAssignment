package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_terms_conditions extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "terms_conditions";

    // Table columns
    public static final String TERMS_CONDITIONS_ID = "terms_conditions_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION  = "description";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + TERMS_CONDITIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT NOT NULL, "
            + DESCRIPTION + " TEXT NOT NULL "
            + ")";

    public dbhelper_terms_conditions(Context context) {
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