package com.andrewxa.todolist.contract;


import com.andrewxa.todolist.data.model.Task;

import java.util.List;

public interface Contract {

    interface IModel {

        boolean addTask(Task task);
        boolean editTask(Task task, long id);
        boolean deleteTask(long id);
    }

     interface IPresenter {

        void onAddTaskButtonClicked(String taskName);
        void onEditTaskButtonClicked(String newTaskName, long id);
        void onDeleteTaskButtonClicked(long id);
        List<Task> getAllTaskClicked();
     }

    interface IView {
        void message(String msg);
        void updateData(List<Task> tasks);
    }

}
