package com.andrewxa.todolist.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.data.database.TaskRepository;
import com.andrewxa.todolist.data.local.TaskDataSource;
import com.andrewxa.todolist.data.local.TaskDatabase;
import com.andrewxa.todolist.data.model.Task;
import com.andrewxa.todolist.data.sqlite.SqliteController;
import com.andrewxa.todolist.data.sqlite.SqliteHelper;

import java.util.List;

public class Presenter implements Contract.Presenter {

    @NonNull
    Contract.view view;
    Context сontext;
    TaskDatabase taskDatabase;
    TaskRepository taskRepository;

    public Presenter(Contract.view view,Context context) {
        this.view = view;
        this.сontext = context;
        taskDatabase = TaskDatabase.getInstance(context);   // Create database
        taskRepository = TaskRepository.getInstance(TaskDataSource.getInstance(taskDatabase.taskDAO()));

    }
    @Override
    public void addTask(String taskName) {
        if(!onCheckTaskClicked(taskName))  {
            incorectTask();
            return;
        }
        Task task = new Task(taskName);
        taskRepository.inserTask(task);
    }

    @Override
    public void editTask(String newTaskName, long id) {
        if(!onCheckTaskClicked(newTaskName)) return;
        Task task = new Task(newTaskName);
        taskRepository.inserTask(task);
    }



/*    @Override
    public void deleteTask(long id) {
        boolean isDeleted  = sqliteController.deleteTask(id);
        if(isDeleted) {
            view.updateData(getAllTask());
            view.message("Task has been deleted");
        }
        else
            Toast.makeText(сontext,"Failed to delete task",Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteTaskById(id);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.getAllTasks();
    }


    public void incorectTask() {
        view.message("Please enter task");
    }

    public boolean onCheckTaskClicked(String task) {
        return task.length() > 1;
    }
}
