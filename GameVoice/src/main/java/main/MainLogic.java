package main;

import gui.Event_follower;
import users.Administrator;
import users.Default_User;
import users.User;
import users.UserContainer;
import java.io.*;
import java.util.*;

/**
 * The MainLogic class represents the core logic of the application,
 * managing user registration, authentication, voting, file operations,
 * and notifying observers of events.
 */
public class MainLogic {
    /*** Container for storing users.*/
    private UserContainer<Default_User> userscontainer = new UserContainer<>();
    /*** List of all votes cast.*/
    private List<Vote> votes;
    /*** Object storing system IDs and passwords.*/
    private IDandPassword idAndPassword;
    /*** Singleton instance of MainLogic.*/
    private static MainLogic instance;
    /*** Username of the current user.*/
    protected String UserHandler;
    /**
     * Flag indicating if voting is over.
     */
    protected boolean VotingIsOver = false;
    /**
     * List of observers monitoring events.
     */
    private List<Event_follower> observers = new ArrayList<>();
    /**
     * Retrieves the singleton instance of MainLogic, creates a new instance if it doesn't exist.
     * @return The singleton instance of MainLogic.
     * @throws CustomExceptionHandler If an error occurs during initialization.
     */
    public static MainLogic getInstance() throws CustomExceptionHandler {
        if (instance == null) {
            instance = new MainLogic(new IDandPassword());
        }
        return instance;
    }
    /**
     * Private constructor to create a new MainLogic instance.
     */
    public MainLogic() {
        this.votes = new ArrayList<>();
    }
    /**
     * Sets the user handler.
     * @param name The username of the user handler.
     */
    public void setUsers(String name){
        this.UserHandler = name;
    }
    /**
     * Checks if voting is over.
     * @return True if voting is over, false otherwise.
     */
    public boolean getVotingIsOver(){
        return this.VotingIsOver;
    }
    /**
     * Sets the flag indicating if voting is over.
     * @param set True to indicate that voting is over, false otherwise.
     */
    public void setVotingIsOver(boolean set){
        this.VotingIsOver = set;
    }
    /**
     * Retrieves the current user handler.
     * @return The username of the user handler.
     */
    public String getUser(){ return this.UserHandler; }
    /**
     * Retrieves the IDandPassword object.
     * @return The IDandPassword object.
     */
    public IDandPassword getIdAndPassword(){
        return this.idAndPassword;
    }
    /**
     * Attaches an observer to the list of observers.
     * @param observer The observer to be attached.
     */
    public void attach(Event_follower observer) {
        observers.add(observer);
    }
    /**
     * Deletes an observer from the list of observers.
     * @param observer The observer to be deleted.
     */
    public void deleteobs(Event_follower observer) { observers.remove(observer); }
    /**
     * Constructs a MainLogic instance with the provided IDandPassword object.
     * @param idAndPassword Object storing system IDs and passwords.
     */
    public MainLogic(IDandPassword idAndPassword) {
        this.idAndPassword = idAndPassword;
        this.votes = new ArrayList<>();
    }
    /**
     * Registers a new user.
     * @param username The username of the new user.
     * @param password The password of the new user.
     */
    public void registerUser(String username, String password) {
        User newUser = new User(username, password);
        userscontainer.addUser(newUser);
    }
    /**
     * Registers a new administrator.
     * @param username The username of the new administrator.
     * @param password The password of the new administrator.
     */
    public void registerAdmin(String username, String password) {
        Administrator newAdmin = new Administrator(username, password);
        userscontainer.addUser(newAdmin);
    }
    /**
     * Checks if a user exists.
     * @param username The username to check.
     * @return True if the user exists, false otherwise.
     */
    public boolean isUserExists(String username) {
        return userscontainer.getUsers().stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }
    /**
     * Validates user credentials.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if the user credentials are valid, false otherwise.
     */
    public boolean isUserValid(String username, String password) {
        for (Default_User user : userscontainer.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Writes user data to the user container.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public void writeUser(String username, String password) {
        if (!isUserExists(username)) {
            User newUser = new User(username, password);
            userscontainer.addUser(newUser);
            System.out.println("User '" + username + "' successfully registered.");
        } else {
            System.out.println("User '" + username + "' already exists.");
        }
    }
    /**
     * Notifies all observers of an event.
     * @param category The category of the event.
     * @param vote The vote associated with the event.
     */
    public void notifyObservers(String category, String vote) {
        observers.forEach(observer -> observer.inform(category, vote));
    }
    /**
     * Deletes a category from the awards file.
     * @param category The category to be deleted.
     */
    public void deleteCategory(String category) {
        try {
            File inputFile = new File("src/main/Data/awards.txt");
            File tempFile = new File("src/main/Data/temp_awards.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(category)) {
                    found = true;
                    // skip category
                    while (!(currentLine = reader.readLine()).isEmpty()) {
                        // skip description and games
                    }
                    // skip an empty separator line
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();

            if (!found) {
                System.out.println("Category not found.");
                return;
            }

            if (!inputFile.delete()) {
                System.out.println("Cannot delete the original file.");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Can't rename a temporary file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Adds a vote for a user in a specific category and game.
     * @param username The username of the user.
     * @param category The category of the vote.
     * @param game The game voted for.
     */
    public void addVote(String username, String category, String game) {
        Vote vote = new Vote(username, category, game);
        votes.add(vote);
    }
    /**
     * Checks if a user has already voted in a specific category.
     * @param username The username of the user.
     * @param category The category to check for voting.
     * @return True if the user has voted in the category, false otherwise.
     */
    public boolean hasVoted(String username, String category) {
        for (Vote vote : votes) {
            if (vote.getUsername().equals(username) && vote.getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Updates the vote of a user in a specific category with a new game.
     * @param username The username of the user.
     * @param category The category of the vote.
     * @param game The new game voted for.
     */
    public void updateVote(String username, String category, String game) {
        // Find and update the vote if needed
        for (Vote vote : votes) {
            if (vote.getUsername().equals(username) && vote.getCategory().equals(category)) {
                vote.setGame(game);
                return;
            }
        }
    }
    /**
     * Computes the winning games for each category based on the highest number of votes.
     * @return A map containing the category as key and the winning game as value.
     */
    public Map<String, String> computeWinningGames() {
        Map<String, String> winningGames = new HashMap<>();

        // Iterate over each category
        for (String category : getAllCategoriesFromVotes()) {
            // Initialize variables to keep track of winning game and its votes
            String winningGame = null;
            int maxVotes = 0;

            // Count votes for each game in the category
            Map<String, Integer> gameVotes = new HashMap<>();
            for (Vote vote : votes) {
                if (vote.getCategory().equals(category)) {
                    String game = vote.getGame();
                    gameVotes.put(game, gameVotes.getOrDefault(game, 0) + 1);
                }
            }

            // Find the game with the maximum votes
            for (Map.Entry<String, Integer> entry : gameVotes.entrySet()) {
                String game = entry.getKey();
                int votesCount = entry.getValue();
                if (votesCount > maxVotes) {
                    maxVotes = votesCount;
                    winningGame = game;
                }
            }

            // Add the winning game to the map
            winningGames.put(category, winningGame);
        }

        return winningGames;
    }
    /**
     * Retrieves all unique categories from the list of votes.
     * @return A list containing all unique categories.
     */
    private List<String> getAllCategoriesFromVotes() {
        // Retrieve all unique categories from the votes list
        Set<String> categories = new HashSet<>();
        for (Vote vote : votes) {
            categories.add(vote.getCategory());
        }
        return new ArrayList<>(categories);
    }
    /**
     * Loads user data from a file and adds it to the user container.
     * @param filePath The path of the file containing user data.
     */
    public void loadUserDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 2) {
                    String username = userData[0];
                    String password = userData[1].trim(); // Trim whitespace
                    if (!userscontainer.containsUser(username)) {
                        if (username.toLowerCase().contains("admin")) {
                            Administrator admin = new Administrator(username, password);
                            userscontainer.addUser(admin);
                        } else {
                            User user = new User(username, password);
                            userscontainer.addUser(user);
                        }
                    }
                } else {
                    System.out.println("Invalid user data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves all votes cast.
     * @return A list of all votes.
     */
    public List<Vote> getAllVotes() {
        return votes;
    }
    /**
     * Sets the list of votes.
     * @param newVotes The list of votes to be set.
     */
    public void setVotes(List<Vote> newVotes) {
        this.votes = newVotes;
    }
    /**
     * Saves the list of votes to a file.
     * @param filename The name of the file to save votes to.
     * @param mainLogic The MainLogic instance containing the votes.
     */
    public static void saveVotesToFile(String filename, MainLogic mainLogic) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(mainLogic.getAllVotes());
            System.out.println("Votes saved to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Loads votes from a file and adds them to the list of votes.
     * @param fileName The name of the file to load votes from.
     */
    public void loadVotesFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = inputStream.readObject();
            if (obj instanceof List) {
                List<Vote> loadedVotes = (List<Vote>) obj;
                this.votes.addAll(loadedVotes);
                System.out.println("Votes loaded from file: " + fileName);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Clears the content of a file.
     * @param fileName The name of the file to be cleared.
     */
    public void clearFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
