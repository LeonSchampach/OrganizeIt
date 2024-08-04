package com.organizeit.db.dto;

public class ItemDto {
    private int id;
    private String name;
    private String desc;
    private float quantity;
    private int drawerId;

    public int getId() {
        return id;
    }

    public ItemDto(int id, String name, String desc, float quantity, int drawerId) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.quantity = quantity;
        this.drawerId = drawerId;
    }

    public ItemDto() {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int getDrawerId() {
        return drawerId;
    }

    public void setDrawerId(int drawerId) {
        this.drawerId = drawerId;
    }
}
