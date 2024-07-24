package com.organizeit.db.dto;

/**
 * The userDTO class serves as a Data Transfer Object (DTO) to represent
 * and transfer user data, primarily in the context of user registration.
 */
public class UserDTO {
    private String firstname;
    private String lastname;
    private String mail;
    private String password;

    /**
     * Parameterized constructor to initialize a userDTO object with
     * all necessary details.
     *
     * @param firstname The user's first name.
     * @param lastname  The user's last name.
     * @param mail      The user's email address.
     * @param password  The user's password.
     */
    public UserDTO(String firstname, String lastname, String mail, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
    }


    /**
     * Default constructor (no-argument constructor) for creating an empty
     * userDTO object.
     */
    public UserDTO() {
    }

    /**
     * Gets the user's first name.
     *
     * @return The user's first name.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the user's first name
     *
     * @param firstname The user's first name.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets the user's last name.
     *
     * @return The user's last name.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastname The user's last name.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets the user's email address.
     *
     * @return The user's email.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the user's email address.
     *
     * @param mail The user's email.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Overrides the toString() method to provide a custom string representation
     * of the userDTO object, useful for debugging and logging.
     *
     * @return A string representation of the userDTO object's data.
     */
    @Override
    public String toString() {
        return "userDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
