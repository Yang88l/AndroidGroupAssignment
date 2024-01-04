package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_train extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "train";

    // Table columns
    public static final String TRAIN_ID = "train_id";
    public static final String USER_ID = "user_id";
    public static final String LOCATION_NAME = "location_name";
    public static final String FROM_WHERE = "from_where";
    public static final String TO_WHERE = "to_where";
    public static final String TIME = "time";
    public static final String COST = "cost";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + TRAIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER NOT NULL, "
            + LOCATION_NAME + " TEXT NOT NULL, "
            + FROM_WHERE + " TEXT NOT NULL, "
            + TO_WHERE + " TEXT NOT NULL, "
            + TIME + " TEXT NOT NULL, "
            + COST + " DOUBLE NOT NULL "
            + ")";

    public dbhelper_train(Context context) {
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