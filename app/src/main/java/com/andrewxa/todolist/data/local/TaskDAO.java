package com.andrewxa.todolist.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.andrewxa.todolist.data.model.Task;

import java.util.List;

@Dao
public interface TaskDAO {

    @Query("SELECT * FROM tasks")
    List<Task> getAllTasks();

    @Query("SELECT * FROM tasks WHERE id=:taskId")
    Task getUserById(long taskId);

    @Insert
    void inserTask(Task... tasks);

    @Update
    void updateTask(Task... tasks);

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM tasks WHERE id=:taskId")
    void deleteTaskById(long taskId);

    @Query("DELETE FROM tasks")
    void deleteAllTasks();
/*
    @Query("DELETE FROM tasks WHERE id=:taskId ")
    void deleteTaskById(long taskId);
*/





}
