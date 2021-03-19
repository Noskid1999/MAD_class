package com.example.todoapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class ETodo {

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

    @ColumnInfo(name = "taskDate")
    Date taskDate;

}
