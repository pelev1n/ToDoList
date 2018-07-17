package com.andrewxa.todolist.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.View;

import com.andrewxa.todolist.adapter.TaskAdapter;
import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.data.model.Task;
import com.andrewxa.todolist.data.sqlite.SqliteController;

public class Presenter implements Contract.IPresenter {

    @NonNull
    Contract.IView mView;
    Context mContext;
    String email, pass;
    SqliteController sqliteController;

    public Presenter() {

    }
    public Presenter(@NonNull Contract.IView view, Context context) {
        mView = view;
        mContext = context;
        sqliteController = new SqliteController(context);
    }

    @Override
    public void onAddTaskButtonClicked(String taskName) {
        Task task = new Task(taskName);

        // Надо добавит True/False что-бы запустить содание taskAdaptera
        boolean isAdded = sqliteController.addTask(task);
        if(isAdded)
            mView.succesAddedTask(sqliteController.getAllTasks());
        else
            mView.unsuccesAddedTask();
    }

    @Override
    public void onEditTaskButtonClicked(String taskName) {
        Task task = new Task(taskName);
        sqliteController.editTask(task);

    }

    @Override
    public void onDeleteTaskButtonClicked(long id) {
      /*  TaskDbHelper taskDbHelper = new TaskDbHelper(context);
        mDatabase = taskDbHelper.getWritableDatabase();
        mDatabase.delete(TaskDbHelper.TABLE,TaskDbHelper.COLUMN_ID+"="+id,null);
        swapCursor(getAllItems());*/

        boolean isDeleted  = sqliteController.deleteTask(id);
    }

    @Override
    public Cursor onGetAllTaskClicked() {
       return sqliteController.getAllTasks();
    }
}
