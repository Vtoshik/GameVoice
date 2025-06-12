package users;
/**
 * The User class represents a regular user with additional voting functionality.
 */
public class User extends Default_User{
    /**
     * The number of votes cast by the user.
     */
    private int number_of_Votes;
    /**
     * Constructs a new User object with the specified username, password, and initializes the number of votes to zero.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        super(username, password);
        this.number_of_Votes = 0; // Initialize votes to zero
    }
    /**
     * Retrieves the number of votes cast by the user.
     * @return The number of votes.
     */
    public int getNumberOfVotes() {
        return number_of_Votes;
    }
    /**
     * Increments the number of votes cast by the user.
     */
    public void incrementVotes() {
        number_of_Votes++;
    }
    /**
     * Displays the user information including username and number of votes.
     */
    public void displayUserInfo() {
        System.out.println("User Information: Username - " + getUsername() + number_of_Votes);
    }
}
