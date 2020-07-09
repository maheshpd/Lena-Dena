package com.example.lenadena.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lenadena.model.Dena;


import java.util.List;

@Dao
public interface DenaDao {
    @Query("SELECT * FROM Dena")
    List<Dena> getAllData();

    @Insert
    void insert(Dena dena);

    @Delete
    void delete(Dena dena);

    @Update
    void update(Dena dena);
}
