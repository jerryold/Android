package com.example.rub.testhomework4;

import android.provider.BaseColumns;

/**
 * Created by Rub on 2017/5/1.
 */

public class SessionManagerContract {
    public static final class SessionManagerEntry implements BaseColumns{
        public static final String TABLE_NAME = "manager";
        public static final String COLUMN_GUEST_NAME = "guestName";
        public static final String COLUMN_GUEST_AGE = "guestAge";
        public static final String COLUMN_GUEST_GENDER = "guestGender";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
