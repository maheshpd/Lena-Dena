package com.example.lenadena.Client;

import android.content.Context;

import androidx.room.Room;

import com.example.lenadena.Abstract.DenaDataBase;

public class DenaDatabaseClient {
    private static DenaDatabaseClient mInstance;
    private Context mContext;
    private DenaDataBase denaDataBase;

    public DenaDatabaseClient(Context context) {
        this.mContext = context;
        denaDataBase = Room.databaseBuilder(mContext, DenaDataBase.class, "DenaTable").build();

    }

    public static synchronized DenaDatabaseClient getInstance(Context mContext) {
        if (mInstance == null)
            mInstance = new DenaDatabaseClient(mContext);
        return mInstance;
    }

    public DenaDataBase getDenaDataBase() {
        return denaDataBase;
    }
}
