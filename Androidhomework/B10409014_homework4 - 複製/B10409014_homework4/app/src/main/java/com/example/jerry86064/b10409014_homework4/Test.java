package com.example.jerry86064.b10409014_homework4;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry86064 on 2017/05/07.
 */

public class Test {
    public void insertFakeData(SQLiteDatabase db){
        if(db==null){
            System.out.println("dberror");
            return;
        }
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();

        cv.put(Db_contract.ManagerEntry.COLUMN_GUEST_NAME , "Lin");
        cv.put(Db_contract.ManagerEntry.COLUMN_GUEST_AGE , 48);
        cv.put(Db_contract.ManagerEntry.COLUMN_GUEST_GENDER ,1);
        list.add(cv);
        cv = new ContentValues();
        cv.put(Db_contract.ManagerEntry.COLUMN_GUEST_NAME , "Lee");
        cv.put(Db_contract.ManagerEntry.COLUMN_GUEST_AGE , 20);
        cv.put(Db_contract.ManagerEntry.COLUMN_GUEST_GENDER ,0);
        list.add(cv);
        cv = new ContentValues();
        cv.put(Db_contract.ManagerEntry.COLUMN_GUEST_NAME , "Hung");
        cv.put(Db_contract.ManagerEntry.COLUMN_GUEST_AGE , 10);
        cv.put(Db_contract.ManagerEntry.COLUMN_GUEST_GENDER ,0);
        list.add(cv);
        try {
            db.beginTransaction();
            db.delete(Db_contract.ManagerEntry.TABLE_NAME , null,null);
            for(ContentValues c:list){
                db.insert(Db_contract.ManagerEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }

    }
}
