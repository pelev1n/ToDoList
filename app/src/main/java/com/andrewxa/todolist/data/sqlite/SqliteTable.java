package com.andrewxa.todolist.data.sqlite;

public class SqliteTable {
    public static final String TABLE_USER = "tasks"; // название таблицы в бд
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "task";

    public static final String DB_USER = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY  AUTOINCREMENT, " + COLUMN_NAME
            + " TEXT NOT NULL " + ")";
}
