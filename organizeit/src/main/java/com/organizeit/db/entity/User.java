package com.organizeit.db.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The User class represents a User enrolled in the library system.
 */
@Entity
@Table(name = "END_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    /**
     * Default constructor (no-argument constructor) required for frameworks like Hibernate.
     */
    public User() {
    }

    /**
     * Gets the User's unique ID.
     *
     * @return The User's ID.
     */
    public int getId() {
        return id;
    }
}
