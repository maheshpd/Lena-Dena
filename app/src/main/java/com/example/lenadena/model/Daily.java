package com.example.lenadena.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.realm.RealmObject;

@Entity
public class Daily {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String createDate;
    private String description;
    private String amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
