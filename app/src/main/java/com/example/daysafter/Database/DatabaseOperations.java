package com.example.daysafter.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations
{
    private DatabaseWrapper dbHelper;
    private String[] DAYS_TABLE_COLUMN = {DatabaseWrapper.ID, DatabaseWrapper.TARGET_DATE};
    private SQLiteDatabase objDatabase;

    public DatabaseOperations (Context context)
    {
        dbHelper = new DatabaseWrapper(context);
    }

    public void open () throws SQLException
    {
        objDatabase = dbHelper.getWritableDatabase();
    }

    public void close ()
    {
        dbHelper.close();
    }

    private Days parseDay (Cursor cursor)
    {
        Days day = new Days();
        day.setId(cursor.getInt(0));
        day.setDay(cursor.getString(1));
        return day;
    }

    public Days addDay (String targetDate) {
        ContentValues values = new ContentValues();
        values.put(DatabaseWrapper.TARGET_DATE, targetDate);

        long Id = objDatabase.insert(DatabaseWrapper.TABLE_NAME, null, values);

        Cursor cursor = objDatabase.query(DatabaseWrapper.TABLE_NAME,
                DAYS_TABLE_COLUMN, DatabaseWrapper.ID + " = "
                        + Id, null, null, null, null);

        cursor.moveToFirst();
        Days newComment = parseDay(cursor);
        cursor.close();
        return  newComment;
    }

    public Days deleteAllDays () {
        objDatabase.delete(DatabaseWrapper.TABLE_NAME, null, null);
        return null;
    }

    public List getAllDays(){
        List days = new ArrayList();

        Cursor cursor = objDatabase.query(DatabaseWrapper.TABLE_NAME,
                DAYS_TABLE_COLUMN, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Days day = parseDay(cursor);
            days.add(day);
            cursor.moveToNext();
        }

        cursor.close();
        return days;
    }
}
