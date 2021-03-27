package com.example.todoapp.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todoapp.database.TodoRoomDatabase;
import com.example.todoapp.database.dao.TodoDAO;
import com.example.todoapp.database.model.ETodo;

import java.util.List;

public class TodoRepository {
    private TodoDAO mTodoDAO;
    private LiveData<List<ETodo>> allTodoList;

    public TodoRepository(Application application) {
        TodoRoomDatabase database = TodoRoomDatabase.getInstance(application);
        mTodoDAO = database.mTodoDAO();
        allTodoList = mTodoDAO.getAllTodos();
    }

    public TodoDAO getmTodoDAO() {
        return mTodoDAO;
    }

    public void setmTodoDAO(TodoDAO mTodoDAO) {
        this.mTodoDAO = mTodoDAO;
    }

    public LiveData<List<ETodo>> getAllTodoList() {
        return allTodoList;
    }

    public void setAllTodoList(LiveData<List<ETodo>> allTodoList) {
        this.allTodoList = allTodoList;
    }

    public void insert(ETodo eTodo){
        new insertTodoAsyncTask(mTodoDAO).execute(eTodo);
    }

    public void update(ETodo eTodo){
        new updateTodoAsyncTask(mTodoDAO).execute(eTodo);
    }

//    public void delete(int id){
//        new
//    }


    public static class insertTodoAsyncTask extends AsyncTask<ETodo, Void,Void>{
        private TodoDAO mTodoDAO;
        private insertTodoAsyncTask(TodoDAO mTodoDAO){
            this.mTodoDAO = mTodoDAO;
        }
        @Override
        protected Void doInBackground(ETodo... eTodos){
            mTodoDAO.insert(eTodos[0]);
            return null;
        }
    }
    public static class updateTodoAsyncTask extends AsyncTask<ETodo, Void,Void>{
        private TodoDAO mTodoDAO;
        private updateTodoAsyncTask(TodoDAO mTodoDAO){
            this.mTodoDAO = mTodoDAO;
        }
        @Override
        protected Void doInBackground(ETodo... eTodos){
            mTodoDAO.update(eTodos[0]);
            return null;
        }
    }

}
