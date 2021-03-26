package com.example.todoapp.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.holder.TodoHolder;
import com.example.todoapp.database.model.ETodo;
import com.example.todoapp.viewModel.TodoViewModel;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoHolder>{
    List<ETodo> eTodoList;
    TodoViewModel viewModel;
    public TodoAdapter(List<ETodo> eTodoList){
        this.eTodoList = eTodoList;
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new TodoHolder(layoutInflater,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder holder, int position) {
        ETodo eTodo = eTodoList.get(position);
        holder.bind(eTodo);
    }

    @Override
    public int getItemCount() {
        return eTodoList.size();
    }


}
