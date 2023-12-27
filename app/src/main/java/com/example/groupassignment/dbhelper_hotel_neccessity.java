package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_hotel_neccessity extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "hotel_neccessity";

    // Table columns
    public static final String HOTEL_NECCESSITY_ID = "hotel_neccessity_id";
    public static final String WIFI = "wifi";
    public static final String AIR_CONDITION = "air_condition";
    public static final String USER_ID = "user_id";



    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + HOTEL_NECCESSITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WIFI + " TEXT NOT NULL, "
            + AIR_CONDITION + " TEXT NOT NULL, "
            + USER_ID + " INTEGER NOT NULL "
            + ")";

    public dbhelper_hotel_neccessity(Context context) {
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