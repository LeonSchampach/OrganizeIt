package com.organizeit.db.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Drawer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column (name = "DRAWER_ORDER")
    private int order;

    @Column(name = "shelf_id", nullable = false)
    private long shelfId;

    //Constructor
    public Drawer(String name, int order, int shelfId) {
        this.name = name;
        this.order = order;
        this.shelfId = shelfId;
    }
    public Drawer(){
    }

    // Getters and Setters
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
}
