package com.andrewxa.todolist.data.sqlite;

import android.content.ContentValues;
import android.content.Context;

import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.data.model.Task;

import java.util.List;

public class SqliteController implements Contract.Model {


    private SqliteHelper sqliteHelper;
    Context context;

    public SqliteController(SqliteHelper sqliteHelper) {
        this.sqliteHelper = sqliteHelper;
    }


    @Override
    public boolean addTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(SqliteTable.COLUMN_NAME,task.getName());
        return sqliteHelper.insertData(SqliteTable.TABLE_USER,values);
    }

    @Override
    public boolean editTask(Task task, long id) {
        ContentValues values = new ContentValues();
        values.put(SqliteTable.COLUMN_NAME,task.getName());
        return sqliteHelper.updateData(SqliteTable.TABLE_USER,values,id);
    }

    @Override
    public boolean deleteTask(long id) {
        return sqliteHelper.deleteTask(id);
    }

    public List<Task> getAllTasks() {
        return sqliteHelper.getAllTasks();
    }
}
