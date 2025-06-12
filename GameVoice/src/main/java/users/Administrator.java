package users;
/**
 * The Administrator class represents an administrator user with extended privileges.
 */
public class Administrator extends Default_User{
    /**
     * The access level of the administrator.
     */
    private int accessLevel;
    /**
     * Constructs a new Administrator object with the specified username, password, and default access level.
     * @param username The username of the administrator.
     * @param password The password of the administrator.
     */
    public Administrator(String username, String password) {
        super(username, password);
        // Initialize access level (default or specific value)
        this.accessLevel = 1; // Example: Default access level 1
    }
    /**
     * Retrieves the access level of the administrator.
     * @return The access level.
     */
    public int getAccessLevel() {
        return accessLevel;
    }
    /**
     * Sets the access level of the administrator.
     * @param accessLevel The new access level to be set.
     */
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
