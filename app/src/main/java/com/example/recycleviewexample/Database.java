package com.example.recycleviewexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


public class Database extends SQLiteAssetHelper {
    public static final  String cars_db ="carse.db";
    public static final  int version_db =10;
    public static final  String TABLE_NAME_DB="car";
    public static final  String CAR_COL_ID="id";
    public static final  String CAR_COL_MODEL="model";
    public static final  String CAR_COL_COLOR="color";
    public static final  String CAR_COL_IMG="image";
    public static final  String CAR_COL_DESCRPTION="descreption";
    public static final  String CAR_COL_DISTANCELIT="destiance";


    public Database(Context context)
    {
        super(context, cars_db, null, version_db);
    }
/*
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_DB+"("+CAR_COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                CAR_COL_MODEL+" TEXT,"+CAR_COL_COLOR+" TEXT,"+CAR_COL_DISTANCELIT+" REAL)");

    }
*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME_DB);
       onCreate(db);
    }

}



