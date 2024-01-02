package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_bus extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "bus";

    // Table columns
    public static final String BUS_ID = "bus_id";
    public static final String SEAT = "seat";
    public static final String BUS  = "bus";
    public static final String STATE = "state";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = main.dbversion++;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + BUS_ID + " INTEGER PRIMARY KEY NOT NULL, "
            + SEAT + " INTEGER NOT NULL, "
            + BUS + " TEXT NOT NULL "
            + ")";

    public dbhelper_bus(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL("INSERT INTO bus (BUS_ID, SEAT, BUS) VALUES (1, 30, 'Soutern'), (2, 30, 'Mayang Sari'), (3, 30, 'City Express');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}