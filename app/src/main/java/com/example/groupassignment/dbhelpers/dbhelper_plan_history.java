package com.example.groupassignment.dbhelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_plan_history extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "plan_history";

    // Table columns
    public static final String PLAN_HISTORY_ID = "plan_history_id";
    public static final String USER_ID = "user_id";
    public static final String LOGIN_ID = "login_id";

    public static final String ACTIVITY = "activity";
    public static final String COST = "cost";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + PLAN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER NOT NULL, "
            + LOGIN_ID + " INTEGER NOT NULL, "
            + ACTIVITY + " TEXT NOT NULL, "
            + COST + " TEXT NOT NULL "
            + ")";

    public dbhelper_plan_history(Context context) {
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
