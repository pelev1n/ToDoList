package com.andrewxa.todolist.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.andrewxa.todolist.data.model.Task;

import java.util.ArrayList;
import java.util.List;

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

    public boolean updateData(String table, ContentValues values, long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(table, values, SqliteTable.COLUMN_ID + " = " + id,null) > 0;
    }

    public List<Task> getAllTasks() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor =  db.query(
                SqliteTable.TABLE_USER,
                null,
                null,
                null,
                null,
                null,
                SqliteTable.COLUMN_ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex(SqliteTable.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(SqliteTable.COLUMN_NAME));
                Task task = new Task(id, name);
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tasks;
    }

    public boolean deleteTask(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(SqliteTable.TABLE_USER, SqliteTable.COLUMN_ID + " = " + id,
                null) > 0;
    }



}
