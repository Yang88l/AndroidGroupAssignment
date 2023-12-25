package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_terms_conditions extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "terms_conditions";

    // Table columns
    public static final String TERMS_CONDITIONS_ID = "terms_conditions_id";
    public static final String PRICE = "price";
    public static final String FOOD  = "food";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + TERMS_CONDITIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRICE + " DOUBLE NOT NULL, "
            + FOOD + " TEXT NOT NULL, "
            + ")";

    public dbhelper_terms_conditions(Context context) {
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