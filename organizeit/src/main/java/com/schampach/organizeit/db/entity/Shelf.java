package com.schampach.organizeit.db.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "shelf")
public class Shelf {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "room")
    private String room;

    @OneToMany(mappedBy = "shelf", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Drawer> drawers;

    // Getters and Setters
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

    public Set<Drawer> getDrawers() {
        return drawers;
    }

    public void setDrawers(Set<Drawer> drawers) {
        this.drawers = drawers;
    }
}
