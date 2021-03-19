package com.example.todoapp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TodoDAO {
    @Insert
    void insert(ETodo task);

    @Delete
    void delete(int id);

//    @Update


    @Query("SELECT * FROM etodo WHERE id = :id")
    ETodo get(int id);
}
