package com.organizeit.db.dto;

import java.util.List;

public class DrawerDto {
    private long id;
    private String name;
    private int order;
    private long shelfId;
    private List<ItemDto> items;

    public DrawerDto(long id, String name, int order, long shelfId) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.shelfId = shelfId;
    }

    public DrawerDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getShelfId() {
        return shelfId;
    }

    public void setShelfId(long shelfId) {
        this.shelfId = shelfId;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
