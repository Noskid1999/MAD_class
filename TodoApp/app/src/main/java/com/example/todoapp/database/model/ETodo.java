package com.example.todoapp.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.todoapp.util.DateConverter;

import java.util.Date;

@Entity
public class ETodo {
    @Ignore
    public ETodo(){}

    public ETodo(String title, String description, int priority, int status, Date taskDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.taskDate = taskDate;
    }

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "description")
    String description;

    @ColumnInfo(name = "priority")
    int priority;

    @ColumnInfo(name = "status")
    int status;

    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "taskDate")
    Date taskDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }
}
