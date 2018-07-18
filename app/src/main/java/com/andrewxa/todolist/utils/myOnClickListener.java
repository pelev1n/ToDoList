package com.andrewxa.todolist.utils;

import android.view.View;

public interface myOnClickListener {

    void onItemClick(View view, int position, String passString);

    void onSimpleClick(int position);
}