package com.andrewxa.todolist.data.local;

import com.andrewxa.todolist.data.database.TaskDataSourceIntrf;
import com.andrewxa.todolist.data.model.Task;

import java.util.List;

/*import io.reactivex.Flowable;*/

public class TaskDataSource implements TaskDataSourceIntrf {

    private TaskDAO taskDAO;
    private static TaskDataSource instance;

    public TaskDataSource(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public static TaskDataSource getInstance(TaskDAO taskDAO) {
        if(instance == null) {
            instance = new TaskDataSource(taskDAO);
        }
        return instance;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Override
    public void inserTask(Task... tasks) {
        taskDAO.inserTask(tasks);
    }

    @Override
    public void updateTaskById(String taskName, long taskId) {
        taskDAO.updateTaskById(taskName,taskId);
    }

    @Override
    public void deleteTaskById(long taskId) {
        taskDAO.deleteTaskById(taskId);
    }

    @Override
    public void deleteAllTasks() {
        taskDAO.deleteAllTasks();
    }
}
