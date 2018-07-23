package com.andrewxa.todolist.contract;


import com.andrewxa.todolist.data.model.Task;

import java.util.List;

public interface Contract {

    interface Model {

        boolean addTask(Task task);
        boolean editTask(Task task, long id);
        boolean deleteTask(long id);
    }

     interface Presenter {

        void addTask(String taskName);
        void editTask(String newTaskName, long id);
        void deleteTask(long id);
        List<Task> getAllTask();
     }

    interface view {
        void message(String msg);
        void updateData(List<Task> tasks);
    }

}
