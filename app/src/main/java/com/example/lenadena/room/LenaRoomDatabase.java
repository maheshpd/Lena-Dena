package com.example.lenadena.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lenadena.dao.DaliyDao;
import com.example.lenadena.dao.DenaDao;
import com.example.lenadena.dao.LenaDao;
import com.example.lenadena.model.Daily;
import com.example.lenadena.model.Dena;
import com.example.lenadena.model.Lena;

@Database(entities = {Lena.class, Dena.class, Daily.class}, version = 2)
public abstract class LenaRoomDatabase extends RoomDatabase {
    public abstract LenaDao lenaDao();
    public abstract DenaDao denaDao();
    public abstract DaliyDao daliyDao();
}
