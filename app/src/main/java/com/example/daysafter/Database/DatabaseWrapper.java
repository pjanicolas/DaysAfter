package com.example.daysafter.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseWrapper extends SQLiteOpenHelper
{
    public static final String TABLE_NAME = "Students";
    public static final String ID = "_id";
    public static final String TARGET_DATE = "_targetDate";

    public static final String DATABASE_NAME = "daysAdter.db";
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + TABLE_NAME
            + "(" + ID + " integer primary key autoincrement, "
            + TARGET_DATE + " text not null);";

    public DatabaseWrapper (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate (SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
