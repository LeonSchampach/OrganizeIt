package com.organizeit.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "shelf")
public class Shelf {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "room")
    private String room;

    @OneToMany(mappedBy = "shelf", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Drawer> drawers;

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

    public List<Drawer> getDrawers() {
        return drawers;
    }

    public void setDrawers(List<Drawer> drawers) {
        this.drawers = drawers;
    }
}
