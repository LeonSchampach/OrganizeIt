package com.organizeit.db.entity;

import jakarta.persistence.*;

/**
 * The User class represents a User enrolled in the library system.
 */
@Entity
@Table(name = "END_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


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
    public long getId() {
        return id;
    }
}
