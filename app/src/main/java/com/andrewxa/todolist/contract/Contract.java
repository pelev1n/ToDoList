package com.andrewxa.todolist.contract;


import com.andrewxa.todolist.data.model.Task;

import java.util.List;

/*import io.reactivex.Flowable;*/

public interface Contract {

    interface Model {
        void inserTask(Task... tasks);
        void updateTaskById(String taskName, long taskId);
        void deleteTaskById(long taskId);
        List<Task> getAllTasks();
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
