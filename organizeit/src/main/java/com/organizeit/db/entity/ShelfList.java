package com.organizeit.db.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "SHELF_LIST")
public class ShelfList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    public ShelfList(String name) {
        this.name = name;
    }

    public ShelfList() {
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
}
