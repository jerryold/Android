package com.example.jerry86064.b10409014_homework4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import static com.example.jerry86064.b10409014_homework4.Db_contract.ManagerEntry.TABLE_NAME;

/**
 * Created by jerry86064 on 2017/05/07.
 */

public class Db_helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "manager.db";
    public static final int DATABASE_VERSION = 2;

    public Db_helper(Context context) {
        super(context,DATABASE_NAME ,null ,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_WAITLIST_TABLE = "CREATE TABLE " + Db_contract.ManagerEntry.TABLE_NAME + " (" +
                Db_contract.ManagerEntry._ID + "   INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                Db_contract.ManagerEntry.COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                Db_contract.ManagerEntry.COLUMN_GUEST_GENDER + " INTEGER NOT NULL, " +
                Db_contract.ManagerEntry.COLUMN_GUEST_AGE + "  INTEGER NOT NULL, "+
                Db_contract.ManagerEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        db.execSQL(CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " +   Db_contract.ManagerEntry.TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}