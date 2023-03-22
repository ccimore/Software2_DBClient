package com.c195.model;

/**
 * THe User class creates the blueprint for the User objects.
 */
public class User {

    private int userId;
    private String userName;
    private String userPassword;

    /**
     * The constructor for the User class.
     *
     * @param userId User ID
     * @param userName User Name
     * @param userPassword User Password
     */
    public User(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * Retrieves User ID
     *
     * @return User ID
     */
    public int getUserId() { return userId; }

    /**
     * Sets User ID.
     *
     * @param userId User ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Retrieves User Name.
     *
     * @return User Name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets User Name.
     *
     * @param userName User Name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves User password.
     *
     * @return User password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets user password.
     *
     * @param userPassword User password.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Overrides default toString method to return custom string representation of User object.
     *
     * @return Custom string representation of User object.
     */
    @Override
    public String toString(){
        return (userName + " (ID: " +Integer.toString(userId) + ")");
    }

}
