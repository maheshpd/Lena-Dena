package com.example.lenadena.model;

import java.util.ArrayList;

public class SectionModel {
    public String date;
    public String totalPrice;
    public ArrayList<ItemModel> itemModelArrayList;

    public SectionModel(String date, String totalPrice, ArrayList<ItemModel> itemModelArrayList) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.itemModelArrayList = itemModelArrayList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<ItemModel> getItemModelArrayList() {
        return itemModelArrayList;
    }

    public void setItemModelArrayList(ArrayList<ItemModel> itemModelArrayList) {
        this.itemModelArrayList = itemModelArrayList;
    }
}
