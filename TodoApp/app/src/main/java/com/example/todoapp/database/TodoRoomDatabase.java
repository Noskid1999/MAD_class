package com.example.todoapp.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todoapp.database.dao.TodoDAO;
import com.example.todoapp.database.model.ETodo;

import java.util.Date;

@Database(entities = {ETodo.class}, version = 1, exportSchema = false)
public abstract class TodoRoomDatabase extends RoomDatabase {
    public static final String DB_NAME = "todo";
    public static TodoRoomDatabase INSTANCE;

    public static synchronized TodoRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TodoRoomDatabase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(sCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TodoDAO mTodoDAO();

    public static class populateDbAsync extends AsyncTask<ETodo, Void, Void> {
        private TodoDAO mTodoDAO;

        private populateDbAsync(TodoRoomDatabase db) {
            this.mTodoDAO = db.mTodoDAO();
        }

        @Override
        protected Void doInBackground(ETodo... eTodos) {
            Date date = new Date();
            ETodo eTodo = new ETodo("Demo Title", "Demo Description", 1, 0, date);
            mTodoDAO.insert(eTodo);
            return null;
        }
    }

    private static RoomDatabase.Callback sCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDbAsync(INSTANCE).execute();
        }
    };

}
