package com.andrewxa.todolist.data.database;

import com.andrewxa.todolist.contract.Contract;
import com.andrewxa.todolist.data.model.Task;

import java.util.List;

/*import io.reactivex.Flowable;*/

public interface TaskDataSourceIntrf extends Contract.Model{

    List<Task> getAllTasks();
    void inserTask(Task... tasks);
    void updateTaskById(String taskName, long taskId);
    void deleteTaskById(long taskId);
    void deleteAllTasks();
}
