package com.organizeit.db.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "drawerId", nullable = false)
    private int drawerId;

    //Constructor
    public Item(String name, String desc, int drawerId) {
        this.name = name;
        this.desc = desc;
        this.drawerId = drawerId;
    }
    public Item() {
    }

    //Getters and Setters
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
