package com.organizeit.db.dto;

import java.util.List;

public class ShelfDto {
    private long id;
    private String name;
    private String room;
    private long shelfListId;
    private List<DrawerDto> drawers;

    public ShelfDto(long id, String name, String room, long shelfListId) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.shelfListId = shelfListId;
    }

    public ShelfDto() {
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public long getShelfListId() {
        return shelfListId;
    }

    public void setShelfListId(long shelfListId) {
        this.shelfListId = shelfListId;
    }

    public List<DrawerDto> getDrawers() {
        return drawers;
    }

    public void setDrawers(List<DrawerDto> drawers) {
        this.drawers = drawers;
    }
}
