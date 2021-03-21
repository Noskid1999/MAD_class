package com.example.todoapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todoapp.database.dao.TodoDAO;
import com.example.todoapp.database.model.ETodo;

@Database(entities = {ETodo.class}, version = 1, exportSchema = false)
public abstract class TodoRoomDatabase extends RoomDatabase {
    public static final String DB_NAME = "room";
    public static TodoRoomDatabase INSTANCE;

    public static synchronized TodoRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TodoRoomDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();

                }
            }
        }
        return INSTANCE;
    }

    public abstract TodoDAO mTodoDAO();


}
