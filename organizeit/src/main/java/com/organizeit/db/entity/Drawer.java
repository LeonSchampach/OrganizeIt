package com.organizeit.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Drawer {
    @Id
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "shelf_name", referencedColumnName = "name")
    @JsonBackReference
    private Shelf shelf;

    @OneToMany(mappedBy = "drawer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Item> items;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}