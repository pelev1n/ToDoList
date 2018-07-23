package com.andrewxa.todolist.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.data.model.Task;
import com.andrewxa.todolist.data.sqlite.SqliteController;
import com.andrewxa.todolist.data.sqlite.SqliteHelper;

import java.util.List;

public class Presenter implements Contract.Presenter {

    @NonNull
    Contract.view view;
    Context сontext;
    SqliteController sqliteController;

    public Presenter(@NonNull Contract.view view, Context context) {
        this.view = view;
        this.сontext = context;
        sqliteController = new SqliteController(new SqliteHelper(context));
    }
    @Override
    public void addTask(String taskName) {
        if(!onCheckTaskClicked(taskName))  {
            incorectTask();
            return;
        }
        Task task = new Task(0,taskName);
        boolean isAdded = sqliteController.addTask(task);
        if(isAdded) {
            view.updateData(getAllTask());
            view.message("Task has been added");
        }
        else
            view.message("Failed to edit task");
    }

    @Override
    public void editTask(String newTaskName, long id) {
        if(!onCheckTaskClicked(newTaskName)) return;
        Task task = new Task(id,newTaskName);
        boolean isEdited = sqliteController.editTask(task,id);
        if(isEdited) {
            view.updateData(getAllTask());
            view.message("Task has been edited!");
        }
        else
            view.message("Failed to edit task");

    }

    @Override
    public void deleteTask(long id) {
        boolean isDeleted  = sqliteController.deleteTask(id);
        if(isDeleted) {
            view.updateData(getAllTask());
            view.message("Task has been deleted");
        }
        else
            Toast.makeText(сontext,"Failed to delete task",Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<Task> getAllTask() {
        return sqliteController.getAllTasks();
    }

    public void incorectTask() {
        view.message("Please enter task");
    }

    public boolean onCheckTaskClicked(String task) {
        return task.length() > 1;
    }
}
