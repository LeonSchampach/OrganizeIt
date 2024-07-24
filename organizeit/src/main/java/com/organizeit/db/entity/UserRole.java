package com.organizeit.db.entity;

import jakarta.persistence.*;

/**
 * Represents a role that a user can have within the SmartLibrary system,
 * with an identifier and a description.
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String identifier;

    private String description;

    /**
     * Creates a UserRole object with the specified identifier and description.
     *
     * @param identifier  A unique identifier for the role.
     * @param description A textual description of the role's purpose.
     */
    public UserRole(String identifier, String description) {
        this.identifier = identifier;
        this.description = description;
    }

    /**
     * Default constructor required for frameworks like Hibernate.
     */
    public UserRole() {}

    /**
     * Gets the UserRole's unique ID.
     *
     * @return The UserRole's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the UserRole's unique identifier.
     *
     * @return The UserRole's identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the UserRole's identifier.
     *
     * @param identifier The UserRole's identifier.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the description of the UserRole.
     *
     * @return The description of the UserRole.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the UserRole.
     *
     * @param description The description of the UserRole.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
