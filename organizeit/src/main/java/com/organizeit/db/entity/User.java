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

    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String mail;

    private String password;

    private Integer userRoleId;

    /**
     * Parameterized constructor to create a User object with the specified details.
     *
     * @param firstname The User's first name.
     * @param lastname  The User's last name.
     * @param mail      The User's email address (must be unique).
     * @param password  The User's password.
     */
    public User(String firstname, String lastname, String mail, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
        this.userRoleId = null;
    }

    /**
     * Parameterized constructor to create a User object with the specified details.
     *
     * @param firstname The User's first name.
     * @param lastname  The User's last name.
     * @param mail      The User's email address (must be unique).
     * @param password  The User's password.
     * @param userRoleId The User's userRoleID.
     */
    public User(String firstname, String lastname, String mail, String password, int userRoleId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
        if(userRoleId == 0){
            this.userRoleId = null;
        }else{
            this.userRoleId = userRoleId;
        }

    }

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

    /**
     * Gets the User's first name.
     *
     * @return The User's first name.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the User's first name.
     *
     * @param firstname The User's first name.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets the User's last name.
     *
     * @return The User's last name.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the User's last name.
     *
     * @param lastname The User's last name.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets the User's email address.
     *
     * @return The User's email.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the User's email address (must be unique)
     *
     * @param mail The User's email.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets the User's password. (Note: Consider security practices for storing passwords)
     *
     * @return The User's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the User's password. (Note: Consider security practices for storing passwords)
     *
     * @param password The User's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the User's UserRoleId.
     *
     * @return The User's UserRoleId.
     */
    public int getUserRoleId() {
        return Objects.requireNonNullElse(userRoleId, 0);
    }

    /**
     * Sets the User's userRoleId.
     *
     * @param userRoleId The User's userRoleId.
     */
    public void setUserRoleId(int userRoleId) {
        if(userRoleId == 0)
        {
            this.userRoleId = null;
        }else{
            this.userRoleId = userRoleId;
        }
    }

    /**
     * Overrides the toString() method to provide a custom string representation
     * of the User object, useful for debugging and logging.
     *
     * @return A string representation of the User object's data.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", userRoleId='" + userRoleId + '\'' +
                '}';
    }
}
