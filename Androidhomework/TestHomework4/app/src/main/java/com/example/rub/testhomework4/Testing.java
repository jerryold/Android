package com.example.rub.testhomework4;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rub on 2017/5/2.
 */

public class Testing {


    public void insertFakeData(SQLiteDatabase db){
        if(db==null){
            System.out.println("dberror");
            return;
        }
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();

        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_NAME , "Lin");
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_AGE , 48);
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_GENDER ,1);
        list.add(cv);
        cv = new ContentValues();
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_NAME , "Lee");
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_AGE , 20);
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_GENDER ,0);
        list.add(cv);
        cv = new ContentValues();
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_NAME , "Hung");
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_AGE , 10);
        cv.put(SessionManagerContract.SessionManagerEntry.COLUMN_GUEST_GENDER ,0);
        list.add(cv);
        try {
            db.beginTransaction();
            db.delete(SessionManagerContract.SessionManagerEntry.TABLE_NAME , null,null);
            for(ContentValues c:list){
                db.insert(SessionManagerContract.SessionManagerEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }

    }
}
