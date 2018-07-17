package com.andrewxa.todolist.presenter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.data.model.Task;
import com.andrewxa.todolist.data.sqlite.SqliteController;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements Contract.IPresenter {

    @NonNull
    Contract.IView mView;
    Context mContext;
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
        Task task = new Task(0,taskName);
        boolean isAdded = sqliteController.addTask(task);
        if(isAdded) {
            mView.updateData(getAllTaskClicked());
            mView.message("Task has been added");
        }
        else
            mView.message("Failed to edit task");
    }

    @Override
    public void onEditTaskButtonClicked(String newTaskName,long id) {
        Task task = new Task(id,newTaskName);
        boolean isEdited = sqliteController.editTask(task,id);
        if(isEdited) {
            mView.updateData(getAllTaskClicked());
            mView.message("Task has been edited!");
        }
        else
            mView.message("Failed to edit task");

    }

    @Override
    public void onDeleteTaskButtonClicked(long id) {
        boolean isDeleted  = sqliteController.deleteTask(id);
        if(isDeleted) {
            mView.updateData(getAllTaskClicked());
            mView.message("Task has been deleted");
        }
        else
            Toast.makeText(mContext,"Failed to delete task",Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<Task> getAllTaskClicked() {
        return sqliteController.getAllTasks();
    }
}
