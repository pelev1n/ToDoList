package com.andrewxa.todolist.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.data.database.TaskRepository;
import com.andrewxa.todolist.data.local.TaskDataSource;
import com.andrewxa.todolist.data.local.TaskDatabase;
import com.andrewxa.todolist.data.model.Task;

import java.util.List;

public class Presenter implements Contract.Presenter {

    @NonNull
    Contract.view view;
    TaskDatabase taskDatabase;
    TaskRepository taskRepository;

    public Presenter(Contract.view view,Context context) {
        this.view = view;
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
        view.updateData(getAllTask());
    }

    @Override
    public void editTask(String newTaskName, long id) {
        if(!onCheckTaskClicked(newTaskName)) return;
        taskRepository.updateTaskById(newTaskName,id);
        view.updateData(getAllTask());
    }



    @Override
    public void deleteTask(long id) {
        taskRepository.deleteTaskById(id);
        view.updateData(getAllTask());
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
