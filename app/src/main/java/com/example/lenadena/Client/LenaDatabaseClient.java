package com.example.lenadena.Client;

import android.content.Context;

import androidx.room.Room;

import com.example.lenadena.Abstract.LenaDataBase;

public class LenaDatabaseClient {
    private static LenaDatabaseClient mInstance;
    private Context mContext;
    private LenaDataBase lenaDataBase;

    public LenaDatabaseClient(Context context) {
        this.mContext = context;
        lenaDataBase = Room.databaseBuilder(mContext, LenaDataBase.class, "LenaTable").build();

    }

    public static synchronized LenaDatabaseClient getInstance(Context mContext) {
        if (mInstance == null)
            mInstance = new LenaDatabaseClient(mContext);
        return mInstance;
    }

    public LenaDataBase getLenaDataBase() {
        return lenaDataBase;
    }
}
