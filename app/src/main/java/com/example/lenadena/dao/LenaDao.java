package com.example.lenadena.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lenadena.model.Lena;

import java.util.List;

@Dao
public interface LenaDao {

    @Query("SELECT * FROM Lena")
    List<Lena> getAllLena();

    @Insert
    void insert(Lena lena);

    @Delete
    void delete(Lena lena);

    @Update
    void update(Lena lena);
}
