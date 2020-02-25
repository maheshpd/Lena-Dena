package com.example.lenadena.helper;

import com.example.lenadena.model.Lena;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyLenaHelper {

    Realm realm;
    RealmResults<Lena> lenas;


    public MyLenaHelper(Realm realm) {
        this.realm = realm;
    }

    public void selectFromDB() {
        lenas = realm.where(Lena.class).findAll();
    }

    public ArrayList<Lena> justRefresh() {
        ArrayList<Lena> list = new ArrayList<>();
        for (Lena l : lenas) {
            list.add(l);
        }

        return list;
    }

}
