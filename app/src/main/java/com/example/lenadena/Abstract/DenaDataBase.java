package com.example.lenadena.Abstract;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.lenadena.Converter;
import com.example.lenadena.dao.DenaDao;
import com.example.lenadena.model.Dena;

@Database(entities = {Dena.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class DenaDataBase extends RoomDatabase {

    public abstract DenaDao denaDao();

    private static DenaDataBase denaDataBase;

    // synchronized is use to avoid concurrent access in multithred environment
    public static DenaDataBase getInstance(Context context) {
        if (null == denaDataBase) {
            denaDataBase = buildDatabaseInstance(context);
        }
        return denaDataBase;
    }

    private static DenaDataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                DenaDataBase.class,
                "Dena").allowMainThreadQueries().build();
    }

    public void cleanUp() {
        denaDataBase = null;
    }

}
