package com.andrewxa.todolist.contract;


import com.andrewxa.todolist.data.model.Task;
import com.arellomobile.mvp.MvpView;

import java.util.List;
import io.reactivex.Flowable;


public interface Contract {

    interface Model {
        void inserTask(Task... tasks);
        void updateTaskById(String taskName, long taskId);
        void deleteTaskById(long taskId);
        Flowable<List<Task>> getAllTasks();
    }

    interface Presenter {

        void addTask(String taskName);
        void editTask(String newTaskName, long id);
        void deleteTask(long id);
        Flowable<List<Task>> getAllTask();
    }
}
