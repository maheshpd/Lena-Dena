package com.example.lenadena.model;

import java.util.List;

public class SectionModel {
    public String date;
    public List<ItemModel> itemModelArrayList;

    public SectionModel(String date, List<ItemModel> itemModelArrayList) {
        this.date = date;
        this.itemModelArrayList = itemModelArrayList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ItemModel> getItemModelArrayList() {
        return itemModelArrayList;
    }

    public void setItemModelArrayList(List<ItemModel> itemModelArrayList) {
        this.itemModelArrayList = itemModelArrayList;
    }
}
