package main;

import java.io.Serializable;
/**
 * The Vote class represents a vote cast by a user.
 */
public class Vote implements Serializable {
    /**
     * The username of the user who cast the vote.
     */
    private final String username;
    /**
     * The category for which the vote is cast.
     */
    private final String category;
    /**
     * The game voted for.
     */
    private String game;
    /**
     * Constructs a new Vote object with the specified username, category, and game.
     * @param username The username of the user who cast the vote.
     * @param category The category for which the vote is cast.
     * @param game The game voted for.
     */
    public Vote(String username, String category, String game) {
        this.username = username;
        this.category = category;
        this.game = game;
    }
    /**
     * Retrieves the username of the user who casted the vote.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Retrieves the game voted for.
     * @return The game.
     */
    public String getGame(){
        return game;
    }
    /**
     * Retrieves the category for which the vote is casted.
     * @return The category.
     */
    public String getCategory(){
        return category;
    }
    /**
     * Sets the game voted for.
     * @param game The new game to be set.
     */
    public void setGame(String game){
        this.game = game;
    }
    /**
     * Generates a string representation of the Vote object.
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "Vote{" +
                "username='" + username + '\'' +
                ", category='" + category + '\'' +
                ", game='" + game + '\'' +
                '}';
    }
}