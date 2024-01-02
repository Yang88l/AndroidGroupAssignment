package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_play_info extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "play_info";

    // Table columns
    public static final String PLAY_ID = "play_id";
    public static final String PLAY = "play";
    public static final String PRICE  = "price";
    public static final String PICTURE  = "picture";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = main.dbversion++;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + PLAY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PLAY + " TEXT NOT NULL, "
            + PRICE + " DOUBLE NOT NULL, "
            + PICTURE + " TEXT NOT NULL "
            + ")";

    public dbhelper_play_info(Context context) {
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