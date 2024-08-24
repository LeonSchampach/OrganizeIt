package com.organizeit.db.dto;

public class ItemDto {
    private long id;
    private String name;
    private String desc;
    private float quantity;
    private long drawerId;

    public long getId() {
        return id;
    }

    public ItemDto(long id, String name, String desc, float quantity, long drawerId) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.quantity = quantity;
        this.drawerId = drawerId;
    }

    public ItemDto() {
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

    public long getDrawerId() {
        return drawerId;
    }

    public void setDrawerId(long drawerId) {
        this.drawerId = drawerId;
    }
}
