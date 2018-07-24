package com.andrewxa.todolist.data.database;

import com.andrewxa.todolist.data.model.Task;

import java.util.List;

public interface TaskDataSourceIntrf {
    List<Task> getAllTasks();
    Task getUserById(long taskId);
    void inserTask(Task... tasks);
    void updateTask(Task... tasks);
    void deleteTask(Task task);
    void deleteTaskById(long taskId);
    void deleteAllTasks();
}
