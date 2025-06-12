package users;

import java.util.ArrayList;
import java.util.List;
/**
 * The UserContainer class represents a container for storing users.
 * @param <T> The type of users to be stored in the container.
 */
public class UserContainer<T> {
    /**
     * List of users stored in the container.
     */
    private List<T> users;
    /**
     * Constructs a new UserContainer with an empty list of users.
     */
    public UserContainer() {
        users = new ArrayList<>();
    }
    /**
     * Adds a user to the container.
     * @param user The user to be added.
     */
    public void addUser(T user) {
        users.add(user);
    }
    /**
     * Retrieves a user from the container by index.
     * @param index The index of the user.
     * @return The user at the specified index.
     */
    public T getUser(int index) {
        return users.get(index);
    }
    /**
     * Checks if the container contains a user with the specified username.
     * @param username The username of the user to check.
     * @return True if the container contains the user, false otherwise.
     */
    public boolean containsUser(String username) {
        for (T user : users) {
            if (user instanceof User) {
                User currentUser = (User) user;
                if (currentUser.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Retrieves the list of users stored in the container.
     * @return The list of users.
     */
    public List<T> getUsers() {
        return users;
    }

}