package com.organizeit.db.dto;

/**
 * The LoginDTO class serves as a Data Transfer Object (DTO) to encapsulate user
 * login credentials (email and password).
 */
public class LoginDTO {
    private String mail;
    private String password;

    /**
     * Gets the user's email address.
     *
     * @return The user's email.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the user's email address
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
}
