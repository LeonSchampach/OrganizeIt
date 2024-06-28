package com.organizeit.db.dto;

import java.util.List;

public class ShelfDto {
    private String name;
    private String room;
    private List<DrawerDto> drawers;

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

    public List<DrawerDto> getDrawers() {
        return drawers;
    }

    public void setDrawers(List<DrawerDto> drawers) {
        this.drawers = drawers;
    }
}