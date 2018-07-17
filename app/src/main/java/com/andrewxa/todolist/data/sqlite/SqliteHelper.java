package com.andrewxa.todolist.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.andrewxa.todolist.data.model.Task;

public class SqliteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "taskstore.db"; // название бд
    private static final int DATABASE_VERSION = 1; // версия базы данных

    private static final String TAG = SqliteHelper.class.getSimpleName();


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqliteTable.DB_USER);

/*        db.execSQL("CREATE TABLE tasks (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT NOT NULL " + ");");*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+SqliteTable.TABLE_USER);
        onCreate(db);
    }


    public boolean insertData(String table, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(table, null, values);
        if (result == -1){
            Log.d(TAG, "failed to save data!");
            return false;
        }else{
            Log.d(TAG, "save data successful");
            return true;
        }
    }

/*    *//**
     * This method edit the user details and it return int value.
     * @param table this param provide table that you want to update.
     * @param values values param provide col value.
     * @param email to identify the you use want to edit.
     * @return int value.
     **//*
    public int updateData(String table, ContentValues values,
                          String email){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(table, values,
                SqliteTable.COL_USER_EMAIL + " =? ",
                new String[]{email});
    }*/


    public Cursor getAllTasks() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(
                SqliteTable.TABLE_USER,
                null,
                null,
                null,
                null,
                null,
                SqliteTable.COLUMN_ID + " DESC"
        );
    }

    public boolean deleteTask(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(SqliteTable.TABLE_USER, SqliteTable.COLUMN_ID + " = " + id,
                null) > 0;
    }



}
