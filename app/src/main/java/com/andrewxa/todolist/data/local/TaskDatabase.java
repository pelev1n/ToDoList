package com.andrewxa.todolist.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.andrewxa.todolist.data.model.Task;

import static com.andrewxa.todolist.data.local.TaskDatabase.DATABASE_VERSION;

@Database(entities = {Task.class}, version = DATABASE_VERSION)
public abstract class TaskDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Tasks-Database-Room";

    public abstract TaskDAO taskDAO();

    private static TaskDatabase instance;

    public static TaskDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context,TaskDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}