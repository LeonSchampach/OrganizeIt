package com.organizeit.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "drawer_name", referencedColumnName = "name")
    @JsonBackReference
    private Drawer drawer;

    //Constructor
    public Item(String name, String desc, Drawer drawer) {
        this.name = name;
        this.desc = desc;
        this.drawer = drawer;
    }
    public Item() {
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Drawer getDrawer() {
        return drawer;
    }

    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }
}
