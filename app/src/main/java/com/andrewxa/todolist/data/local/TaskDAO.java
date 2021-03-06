package com.andrewxa.todolist.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.andrewxa.todolist.data.model.Task;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface TaskDAO {

    @Query("SELECT * FROM tasks")
    Flowable<List<Task>> getAllTasks();

    @Insert
    void inserTask(Task... tasks);

    @Query("UPDATE tasks SET name=:taskName WHERE id=:taskId")
    void updateTaskById(String taskName, long taskId);

    @Query("DELETE FROM tasks WHERE id=:taskId")
    void deleteTaskById(long taskId);

    @Query("DELETE FROM tasks")
    void deleteAllTasks();

}
