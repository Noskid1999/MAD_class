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

    @Query("SELECT * FROM etodo WHERE id = :id")
    ETodo get(int id);

    @Query("SELECT * FROM etodo ORDER BY taskDate desc,status, priority desc")
    LiveData<List<ETodo>> getAllTodos();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ETodo... todo);

    @Delete
    void delete(ETodo... eTodo);

    @Query("DELETE FROM etodo")
    void deleteAll();

    @Query("DELETE FROM etodo WHERE status = :status")
    void deleteWithStatus(int status);


}
