package com.example.jerry86064.b10409014_homework4;

import android.provider.BaseColumns;

/**
 * Created by jerry86064 on 2017/05/07.
 */

public class Db_contract {
    public static final class ManagerEntry implements BaseColumns {
        public static final String TABLE_NAME = "Manager";
        public static final String COLUMN_GUEST_NAME = "GuestName";
        public static final String COLUMN_GUEST_AGE = "GuestAge";
        public static final String COLUMN_GUEST_GENDER = "GuestGender";
        public static final String COLUMN_TIMESTAMP = "Timestamp";
    }
}
