package com.example.groupassignment.dbhelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_plan_summary extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "plan_sumary";

    // Table columns
    public static final String PLAN_SUMMARY_ID = "plan_summary_id";
    public static final String TYPE = "type";
    public static final String ACTIVITY_ID = "activity_id";
    public static final String USER_ID = "user_id";
    public static final String LOGIN_ID = "login_id";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + PLAN_SUMMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TYPE + " TEXT NOT NULL, "
            + ACTIVITY_ID + " INTEGER NOT NULL, "
            + USER_ID + " INTEGER NOT NULL, "
            + LOGIN_ID + " INTEGER NOT NULL "
            + ")";

    public dbhelper_plan_summary(Context context) {
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
