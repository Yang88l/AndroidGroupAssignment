package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper_food_info extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "food_info";

    // Table columns
    public static final String FOOD_ID = "food_id";
    public static final String FOOD = "food";
    public static final String PRICE  = "price";
    public static final String PICTURE  = "picture";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    public static int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FOOD + " TEXT NOT NULL, "
            + PRICE + " DOUBLE NOT NULL, "
            + PICTURE + " TEXT NOT NULL "
            + ")";

    public dbhelper_food_info(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL("INSERT INTO food_info (food_id, food, price)"+
                "VALUES (1, 'Cielo KL', 150, cielo_kl_77),"+
                "(2, 'Horizon Grill', 130, horizon_grill_77),"+
                "(3, 'Sky Bar', 180, sky_bar_77);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}