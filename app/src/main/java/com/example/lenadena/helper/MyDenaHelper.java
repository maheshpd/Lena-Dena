package com.example.lenadena.helper;

import com.example.lenadena.model.Dena;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyDenaHelper {

    Realm realm;
    RealmResults<Dena> denas;


    public MyDenaHelper(Realm realm) {
        this.realm = realm;
    }

    public void selectFromDB() {
        denas = realm.where(Dena.class).findAll();
    }

    public ArrayList<Dena> justRefresh() {
        ArrayList<Dena> list = new ArrayList<>();
        for (Dena d : denas) {
            list.add(d);
        }

        return list;
    }

}
