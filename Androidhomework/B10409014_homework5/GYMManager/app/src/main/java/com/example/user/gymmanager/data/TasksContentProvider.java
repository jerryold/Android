package com.example.user.gymmanager.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by B10409038 on 2017/5/28.
 */

public class TasksContentProvider extends ContentProvider {

    private static final String AUTHORITY =
            "com.example.user.gymmanager.data.TasksContentProvider";
    private static final String DB_FILE = "tasks.db",
            DB_TABLE = "tasks";
    private static final int URI_ROOT = 0,
            DB_TABLE_TASKS = 1;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + DB_TABLE);
    private static final UriMatcher sUriMatcher = new UriMatcher(URI_ROOT);

    static {
        sUriMatcher.addURI(AUTHORITY, DB_TABLE, DB_TABLE_TASKS);
    }

    private SQLiteDatabase mTaskDb;

    @Override
    public boolean onCreate() {
        TaskDbOpenHelper taskDbOpenHelper = new TaskDbOpenHelper(
                getContext(), DB_FILE, null, 1);
        mTaskDb = taskDbOpenHelper.getWritableDatabase();

        Cursor cursor = mTaskDb.rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name='" +
                        DB_TABLE + "'", null);

        if (cursor != null) {
            if (cursor.getCount() == 0)
                mTaskDb.execSQL("CREATE TABLE " + DB_TABLE + "(" +
                        "_id INTEGER PRIMARY KEY," +
                        "task TEXT NOT NULL," +
                        "time TEXT NOT NULL," +
                        "status INTEGER NOT NULL);");

            cursor.close();
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (sUriMatcher.match(uri) != DB_TABLE_TASKS) {
            throw new IllegalArgumentException("Unknown URI" + uri);
        }

        Cursor c = mTaskDb.query(true, DB_TABLE, projection,
                selection, null, null, null, "time", null);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (sUriMatcher.match(uri) != DB_TABLE_TASKS) {
            throw new IllegalArgumentException("Unknown URI" + uri);
        }

        long rowId = mTaskDb.insert(DB_TABLE, null, values);
        Uri insertedRowUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
        getContext().getContentResolver().notifyChange(insertedRowUri, null);

        return insertedRowUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (sUriMatcher.match(uri) != DB_TABLE_TASKS) {
            throw new IllegalArgumentException("Unknown URI" + uri);
        }

        int tasksDeleted;
        tasksDeleted = mTaskDb.delete(DB_TABLE, selection, null);

        if (tasksDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return tasksDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (sUriMatcher.match(uri) != DB_TABLE_TASKS) {
            throw new IllegalArgumentException("Unknown URI" + uri);
        }
        mTaskDb.update(DB_TABLE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }
}
