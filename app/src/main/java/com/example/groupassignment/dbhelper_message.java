package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_message extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "message";

    // Table columns
    public static final String MESSAGE_ID = "message_id";
    public static final String NAME = "name";
    public static final String MESSAGE = "message";
    public static final String USER_ID = "user_id";


    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = main.dbversion++;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT NOT NULL, "
            + MESSAGE + " TEXT NOT NULL, "
            + USER_ID + " INTEGER NOT NULL "
            + ")";

    public dbhelper_message(Context context) {
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