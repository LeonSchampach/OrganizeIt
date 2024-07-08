package com.organizeit.db.dto;

import com.organizeit.db.entity.Item;

import java.util.List;

public class DrawerDto {
    private int id;
    private String name;
    private int shelfId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }
}
