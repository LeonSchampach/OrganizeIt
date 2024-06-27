package com.organizeit.errorhandling;

/**
 * The ErrorMessages enum provides centralized error messages used
 * throughout the SmartLibrary application.
 */
public enum ErrorMessages {
    INSTANCE;

    /**
     * Returns an error message indicating an internal server error
     * preventing a data modification operation from completing.
     *
     * @return The internal server error message.
     */
    public String getInternalServerErrorString() {
        return "\"Error\":\"Internal Server Error!\"";
    }

    /**
     * Returns a generic error message used when an exception is caught
     * in a try-catch block but more specific error information is unavailable.
     *
     * @return The generic try-catch error message.
     */
    public String getTryCatchErrorString() {
        return "\"Error\":\"General Try-Catch Error!\"";
    }

    /**
     * Returns an error message indicating that a login attempt failed
     * because the user credentials could not be found.
     *
     * @return The login error message.
     */
    public String getLoginError() {
        return "\"Error\":\"User does not exist!\"";
    }
}
