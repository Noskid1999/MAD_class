package com.example.todoapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.todoapp.database.model.ETodo;
import com.example.todoapp.database.repository.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository mTodoRepository;
    private LiveData<List<ETodo>> allTodos;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        mTodoRepository = new TodoRepository(application);
        allTodos = mTodoRepository.getAllTodoList();
    }

    public LiveData<List<ETodo>> getAllTodos() {
        return allTodos;
    }

    public void insert(ETodo todo) {
        mTodoRepository.insert(todo);
    }

    public ETodo get(int id) {
        return mTodoRepository.get(id);
    }

    public void update(ETodo todo) {
        mTodoRepository.update(todo);
    }

    public void delete(ETodo eTodo) {
        mTodoRepository.delete(eTodo);
    }

}
