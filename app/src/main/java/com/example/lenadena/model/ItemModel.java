package com.example.lenadena.model;

public class ItemModel {
    public String description;
    public String price;

    public ItemModel(String description, String price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
