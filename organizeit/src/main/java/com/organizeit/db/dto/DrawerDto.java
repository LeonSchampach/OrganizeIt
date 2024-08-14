package com.organizeit.db.dto;

import com.organizeit.db.entity.Item;

import java.util.List;

public class DrawerDto {
    private int id;
    private String name;
    private int order;
    private int shelfId;
    private List<ItemDto> items;

    public DrawerDto(int id, String name, int order, int shelfId) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.shelfId = shelfId;
    }

    public DrawerDto() {
    }

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
