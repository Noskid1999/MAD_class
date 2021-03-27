package com.example.todoapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.database.model.ETodo;

import java.util.List;

@Dao
public interface TodoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ETodo task);

//    @Delete
//    void delete(int id);

    @Query("SELECT * FROM etodo WHERE id = :id")
    ETodo get(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ETodo... todo);

    @Query("SELECT * FROM etodo ORDER BY taskDate desc,status, priority desc")
    LiveData<List<ETodo>> getAllTodos();

}
