package com.example.todoapp.holder;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.database.model.ETodo;

import java.text.SimpleDateFormat;

public class TodoHolder extends RecyclerView.ViewHolder {

    public static final int HIGH_PRIORITY = 1;
    public static final int MEDIUM_PRIORITY = 2;
    public static final int LOW_PRIORITY = 3;

    TextView tvTitle, tvDate, tvDescription;

    public TodoHolder(View view, ViewGroup parent) {
        super(view);
        tvTitle = itemView.findViewById(R.id.tv_lit_taskTitle);
        tvDate = itemView.findViewById(R.id.tv_lit_taskDate);
        tvDescription = itemView.findViewById(R.id.tv_lit_taskDescription);
    }

    public void bind(ETodo eTodo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tvTitle.setText(eTodo.getTitle());
        tvDate.setText(sdf.format(eTodo.getTaskDate()));
        tvDescription.setText(eTodo.getDescription());

        if(eTodo.getStatus() == 1){
            tvTitle.setPaintFlags(tvTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        itemView.setClipToOutline(true);

        switch (eTodo.getPriority()) {
            case HIGH_PRIORITY:
                itemView.setBackground(itemView.getResources().getDrawable(R.drawable.border_background_danger));
                break;
            case MEDIUM_PRIORITY:
                itemView.setBackground(itemView.getResources().getDrawable(R.drawable.border_background_warning));
                break;
            case LOW_PRIORITY:
                itemView.setBackground(itemView.getResources().getDrawable(R.drawable.border_background_success));
                break;
        }

    }
}
