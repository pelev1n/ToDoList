package com.andrewxa.todolist.contract;

import com.andrewxa.todolist.data.model.Task;
import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.reactivex.Flowable;

public interface ToDoView extends MvpView {

    void message(String msg);
    void updateData(Flowable<List<Task>> tasks);
}
