package com.example.lenadena.Abstract;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.lenadena.Converter;
import com.example.lenadena.dao.LenaDao;
import com.example.lenadena.model.Lena;

@Database(entities = {Lena.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class LenaDataBase extends RoomDatabase {

    public abstract LenaDao lenaDao();
}
