package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_plan_history extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "plan_history";

    // Table columns
    public static final String PLAN_HISTORY_ID = "plan_history_id";
    public static final String USER_ID = "user_id";
    public static final String LOCATION = "location";
    public static final String COST = "cost";
    public static final String DATE = "date";
    public static final String STATUS = "status";
    public static final String PLAN_SUMMARY_ID = "plan_summary_id";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + PLAN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER NOT NULL, "
            + LOCATION + " TEXT NOT NULL, "
            + COST + " DOUBLE PRIMARY KEY AUTOINCREMENT, "
            + DATE + " DATE NOT NULL, "
            + STATUS + " TEXT NOT NULL, "
            + PLAN_SUMMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
