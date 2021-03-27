package com.example.todoapp.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.holder.TodoHolder;
import com.example.todoapp.database.model.ETodo;
import com.example.todoapp.viewModel.TodoViewModel;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoHolder> {
    List<ETodo> eTodoList;

    RecyclerView rvTodoList;

    public TodoAdapter(List<ETodo> eTodoList) {
        this.eTodoList = eTodoList;

    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        rvTodoList = parent.findViewById(R.id.rv_ftl_todoListContainer);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_todo, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int test = rvTodoList.getChildLayoutPosition(v);
                ETodo item = eTodoList.get(test);
            }
        });
        return new TodoHolder(view, parent);
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
