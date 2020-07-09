package com.example.lenadena.client;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.lenadena.room.LenaRoomDatabase;

public class DatabaseClient {
    private Context mContext;
    private static DatabaseClient mInstance;

    private LenaRoomDatabase lenaRoomDatabase;

   /* static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Daily` (`id`,"
                    + " `createDate` TEXT, `description` TEXT, `amount` TEXT, PRIMARY KEY(`id`))");
        }
    };*/

    public DatabaseClient(Context context) {
        this.mContext = context;
        lenaRoomDatabase = Room.databaseBuilder(mContext,LenaRoomDatabase.class,"LenaDena")
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mContext);
        }
        return mInstance;
    }

    public LenaRoomDatabase getLenaRoomDatabase() {
        return lenaRoomDatabase;
    }
}
