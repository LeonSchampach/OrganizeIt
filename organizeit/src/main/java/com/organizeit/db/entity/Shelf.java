package com.organizeit.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "SHELF")
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "room")
    private String room;

    @Column(name = "SHELF_LIST_ID")
    private int shelfListId;

    //Constructor
    public Shelf(String name, String room) {
        this.name = name;
        this.room = room;
    }

    public Shelf(int id, String name, String room, int shelfListId) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.shelfListId = shelfListId;
    }

    public Shelf() {
    }

    // Getters and Setters
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getShelfListId() {
        return shelfListId;
    }

    public void setShelfListId(int shelfListId) {
        this.shelfListId = shelfListId;
    }
}
