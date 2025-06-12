package users;

/**
 * The Default_User class represents a default user with a username and password.
 */
public class Default_User {
    /**
     * The username of the user.
     */
    private String username;
    /**
     * The password of the user.
     */
    private String password;
    /**
     * Constructs a new Default_User object with the specified username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public Default_User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * Constructs a new Default_User object with empty username and password.
     */
    public Default_User() {
        this.username = "";
        this.password = "";
    }
    /**
     * Retrieves the username of the user.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the user.
     * @param username The new username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Retrieves the password of the user.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the user.
     * @param password The new password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Generates a string representation of the Default_User object.
     * @return The string representation.
     */
    // Override toString() for better representation
    @Override
    public String toString() {
        return "Default_User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    /**
     * Checks if this Default_User object is equal to another object.
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Default_User)) return false;
        Default_User that = (Default_User) o;
        return getUsername().equals(that.getUsername()) && getPassword().equals(that.getPassword());
    }
}