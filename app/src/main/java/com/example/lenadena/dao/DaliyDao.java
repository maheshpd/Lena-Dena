package com.example.lenadena.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lenadena.model.Daily;
import com.example.lenadena.model.SectionModel;

import java.util.List;

@Dao
public interface DaliyDao {

    @Query("SELECT * FROM Daily")
    List<Daily> getAllData();

    @Insert
    void insert(Daily daily);

    @Delete
    void delete(Daily daily);

    @Update
    void update(Daily daily);

    @Query("SELECT DISTINCT createDate FROM Daily")
    List<SectionModel> getCreateDate();
}
