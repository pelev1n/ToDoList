package com.andrewxa.todolist.contract;

import android.database.Cursor;

import com.andrewxa.todolist.data.model.Task;

public interface Contract {

    interface IModel {

        boolean addTask(Task task);

        boolean editTask(Task task);

        boolean deleteTask(long id);
    }

     interface IPresenter {

        void onAddTaskButtonClicked(String taskName);
        void onEditTaskButtonClicked(String taskName);
        void onDeleteTaskButtonClicked(long id);

         Cursor onGetAllTaskClicked();
     }

    interface IView {

        void succesAddedTask(Cursor cursor);

        void unsuccesAddedTask();
    }

}
