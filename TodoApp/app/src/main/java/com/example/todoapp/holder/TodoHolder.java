package com.example.todoapp.holder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.database.model.ETodo;

import java.text.SimpleDateFormat;

public class TodoHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvDate;
    public TodoHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.list_item_todo, parent, false));
        tvTitle = itemView.findViewById(R.id.tv_lit_taskTitle);
        tvDate = itemView.findViewById(R.id.tv_lit_taskDate);
    }

    public void bind(ETodo eTodo){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tvTitle.setText(eTodo.getTitle());
        tvDate.setText(sdf.format(eTodo.getTaskDate()));
    }
}
