package com.example.todoapp.holder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;

public class TodoHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvDate;
    public TodoHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.list_item_todo, parent, false));
        tvTitle = itemView.findViewById(R.id.tv_lit_taskTitle);
        tvDate = itemView.findViewById(R.id.tv_lit_taskDate);
    }
}
