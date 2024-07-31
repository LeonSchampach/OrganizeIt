package com.organizeit.db.dto;

import java.util.List;

public class ShelfDto {
    private int id;
    private String name;
    private String room;
    private int shelfListId;
    private List<DrawerDto> drawers;

    public ShelfDto(int id, String name, String room, int shelfListId) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.shelfListId = shelfListId;
    }

    public ShelfDto() {
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getShelfListId() {
        return shelfListId;
    }

    public void setShelfListId(int shelfListId) {
        this.shelfListId = shelfListId;
    }

    public List<DrawerDto> getDrawers() {
        return drawers;
    }

    public void setDrawers(List<DrawerDto> drawers) {
        this.drawers = drawers;
    }
}
