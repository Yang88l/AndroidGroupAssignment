package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_choose_transport extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "choose_transport";

    // Table columns
    public static final String CHOOSE_TRANSPORT_ID = "choose_transport_id";
    public static final String TRANSPORT_ID = "transport_id";
    public static final String USER_ID = "user_id";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + CHOOSE_TRANSPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TRANSPORT_ID + " INTEGER NOT NULL, "
            + USER_ID + " INTEGER NOT NULL, "
            + ")";

    public dbhelper_choose_transport(Context context) {
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
