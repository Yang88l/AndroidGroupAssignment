package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_book_summary extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "book_summary";

    // Table columns
    public static final String BOOK_SUMMARY_ID = "book_summary_id";
    public static final String HOTEL_ID = "hotel_id";
    public static final String USER_ID = "user_id";
    public static final String AIRLINE_ID = "airline_id";
    public static final String TRANSPORT_ID = "transport_id";
    public static final String FOOD_ID = "food_id";
    public static final String TOTAL_PRICE = "total_price";
    public static final String LOCATION = "location";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + BOOK_SUMMARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HOTEL_ID + " INTEGER NOT NULL, "
            + USER_ID + " INTEGER NOT NULL, "
            + AIRLINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TRANSPORT_ID + " INTEGER NOT NULL, "
            + FOOD_ID + " INTEGER NOT NULL, "
            + TOTAL_PRICE + " DOUBLE NOT NULL, "
            + LOCATION + " TEXT NOT NULL "
            + ")";

    public dbhelper_book_summary(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
       // db.execSQL("INSERT INTO booking_summary (USER_ID, AIRLINE_ID, TRANSPORT_ID, FOOD_ID, TOTAL_PRICE, LOCATION) VALUES (1,1,1,1,10,'KAJANG');");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
