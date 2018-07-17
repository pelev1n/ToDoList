package com.andrewxa.todolist.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.data.model.Task;

public class SqliteController implements Contract.IModel {


    private SqliteHelper sqliteHelper;
    Context context;

    public SqliteController(Context mContext) {
        context = mContext;
        sqliteHelper = new SqliteHelper(context);
    }


    @Override
    public boolean addTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(SqliteTable.COLUMN_NAME,task.getName());
        return sqliteHelper.insertData(SqliteTable.TABLE_USER,values);
        /*mAdapter.swapCursor(getAllItems());*/
    }

    @Override
    public boolean editTask(Task task) {
       return addTask(task);
    }

    @Override
    public boolean deleteTask(long id) {
        return sqliteHelper.deleteTask(id);
    }

    public Cursor getAllTasks() {
        return sqliteHelper.getAllTasks();
    }
}
