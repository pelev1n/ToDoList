package com.andrewxa.todolist.data.database;

import com.andrewxa.todolist.data.model.Task;

import java.util.List;

public class TaskRepository implements TaskDataSourceIntrf {

    private TaskDataSourceIntrf localDataSource;
    private static TaskRepository instance;

    public TaskRepository(TaskDataSourceIntrf localDataSource) {
        this.localDataSource = localDataSource;
    }

    public static TaskRepository getInstance(TaskDataSourceIntrf localDataSource) {
        if(instance == null) {
            instance = new TaskRepository(localDataSource);
        }
        return instance;
    }

    @Override
    public List<Task> getAllTasks() {
        return localDataSource.getAllTasks();
    }

    @Override
    public Task getUserById(long taskId) {
        return localDataSource.getUserById(taskId);
    }

    @Override
    public void inserTask(Task... tasks) {
        localDataSource.inserTask(tasks);
    }

    @Override
    public void updateTask(Task... tasks) {
        localDataSource.updateTask(tasks);
    }

    @Override
    public void deleteTask(Task task) {
        localDataSource.deleteTask(task);
    }

    @Override
    public void deleteTaskById(long taskId) {
        localDataSource.deleteTaskById(taskId);
    }

    @Override
    public void deleteAllTasks() {
        localDataSource.deleteAllTasks();
    }
}
