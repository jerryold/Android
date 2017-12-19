package com.example.rub.testhomework4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rub.testhomework4.SessionManagerContract.*;
/**
 * Created by Rub on 2017/5/1.
 */

public class SManagerDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "manager.db";
    public static final int DATABASE_VERSION = 2;

    public SManagerDbHelper(Context context) {
        super(context,DATABASE_NAME ,null ,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_WAITLIST_TABLE = "CREATE TABLE " + SessionManagerEntry.TABLE_NAME + " (" +
                SessionManagerEntry._ID + "   INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                SessionManagerEntry.COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                SessionManagerEntry.COLUMN_GUEST_GENDER + " INTEGER NOT NULL, " +
                SessionManagerEntry.COLUMN_GUEST_AGE + "  INTEGER NOT NULL, "+
                SessionManagerEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        db.execSQL(CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + SessionManagerEntry.TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
