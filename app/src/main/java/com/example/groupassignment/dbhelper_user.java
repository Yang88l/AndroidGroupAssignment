package com.example.groupassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class dbhelper_user extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "user";

    // Table columns
    public static final String USER_ID = "user_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String BIRTHDAY = "birthday";
    public static final String PASSWORD = "password";
    public static final String PICTURE = "picture";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_TRAVEL_BOOKING.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT NOT NULL, "
            + EMAIL + " TEXT NOT NULL, "
            + PHONE + " TEXT PRIMARY KEY AUTOINCREMENT, "
            + BIRTHDAY + " DATE NOT NULL, "
            + PASSWORD + " TEXT NOT NULL, "
            + PICTURE + " TEXT NOT NULL "
            + ")";

    public dbhelper_user(Context context) {
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
