package com.example.todoapp.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.todoapp.database.TodoRoomDatabase;
import com.example.todoapp.database.dao.TodoDAO;
import com.example.todoapp.database.model.ETodo;

public class TodoRepository {
    private TodoDAO mTodoDAO;

    public TodoRepository(Application application) {
        TodoRoomDatabase database = TodoRoomDatabase.getInstance(application);
        mTodoDAO = database.mTodoDAO();
    }

    public TodoDAO getmTodoDAO() {
        return mTodoDAO;
    }

    public void setmTodoDAO(TodoDAO mTodoDAO) {
        this.mTodoDAO = mTodoDAO;
    }

    public void insert(ETodo eTodo){
        new insertTodoAsyncTask(mTodoDAO).execute(eTodo);
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

}
