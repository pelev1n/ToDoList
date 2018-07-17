package com.andrewxa.todolist.contract;

import android.database.Cursor;

import com.andrewxa.todolist.data.model.Task;

import java.util.HashMap;

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

         HashMap<Long, Task> onGetAllTaskClicked();

         void initial();
     }

    interface IView {

       /* void succesAddedTask(Cursor cursor);*/
        void message(String msg);
        void updateData(Cursor cursor);
    }

}
