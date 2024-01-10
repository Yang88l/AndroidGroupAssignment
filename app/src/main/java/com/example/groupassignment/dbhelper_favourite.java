package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_favourite extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "favourite";

    // Table columns
    public static final String FAVOURITE_ID = "favouite_id";
    public static final String USER_ID = "user_id";
    public static final String FROM_WHERE = "from_where";
    public static final String FROM_ID = "from_id";
    public static final String FAVOURITE = "favourite";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + FAVOURITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INT NOT NULL, "
            + FROM_WHERE + " TEXT NOT NULL, "
            + FROM_ID + " INTEGER NOT NULL, "
            + FAVOURITE + " INTEGER NOT NULL"
            + ")";

    public dbhelper_favourite(Context context) {
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