package com.organizeit.response;

/**
 * The LoginResponse class represents a response object used to communicate the
 * results of a login attempt to the client (e.g., frontend).
 */
public class LoginResponse {
    String message; // A message describing the login result (e.g., "Success" or error reason)
    Boolean status; // Indicates whether the login was successful (true/false)
    int id;         // The ID of the logged-in user (if successful)

    /**
     * Parameterized constructor to create a LoginResponse object with all details.
     *
     * @param message A message describing the result of the login attempt.
     * @param status  True if login succeeded, false otherwise.
     * @param id      The ID of the logged-in user (if login was successful).
     */
    public LoginResponse(String message, Boolean status, int id) {
        this.message = message;
        this.status = status;
        this.id = id;
    }

    /**
     * Parameterized constructor to create a LoginResponse object with a message
     * and login success/failure status.
     *
     * @param message A message describing the result of the login attempt.
     * @param status  True if login succeeded, false otherwise.
     */
    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    /**
     * Default constructor (no-argument constructor).
     */
    public LoginResponse() {
    }

    /**
     * Gets the message describing the login result.
     *
     * @return The login response message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message describing the login result.
     *
     * @param message The login response message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the login status.
     *
     * @return True if login succeeded, false otherwise.
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Sets the login status.
     *
     * @param status True if login succeeded, false otherwise.
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Gets the ID of the logged-in user (if the login succeeded).
     *
     * @return The user's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the logged-in user.
     *
     * @param id The user's ID.
     */
    public void setId(int id) {
        this.id = id;
    }
}
