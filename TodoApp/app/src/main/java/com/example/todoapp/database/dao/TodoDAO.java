package com.example.todoapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.database.model.ETodo;

@Dao
public interface TodoDAO {
    @Insert
    void insert(ETodo task);

    @Delete
    void delete(int id);

    @Update
    void update(ETodo task);

    @Query("SELECT * FROM etodo WHERE id = :id")
    ETodo get(int id);
}
