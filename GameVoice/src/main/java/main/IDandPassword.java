package main;

import java.io.*;
/**
 * The IDandPassword class handles user authentication by reading and writing user credentials from/to a file.
 */
public class IDandPassword {
    /**
     * The file path where user credentials are stored.
     */
    private static final String FILE_NAME = "src/main/Data/programdata.txt";
    /**
     * The delimiter used to separate username and password in the file.
     */
    private static final String DELIMITER = ",";
    /**
     * Constructs a new IDandPassword object and handles file exceptions.
     * @throws CustomExceptionHandler If an error occurs during file handling.
     */
    public IDandPassword() throws CustomExceptionHandler {
        CustomExceptionHandler.handleFileException(new File(FILE_NAME));
    }
    /**
     * Checks if a user exists based on the provided username.
     * @param username The username of the user to check.
     * @return True if the user exists, false otherwise.
     */
    public boolean isUserExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Validates user credentials based on the provided username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if the credentials are valid, false otherwise.
     */
    public boolean isUserValid(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Writes new user credentials to the file.
     * @param username The username of the new user.
     * @param password The password of the new user.
     */
    public void writeNewUser(String username, String password) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(username + DELIMITER + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves the file name where user credentials are stored.
     * @return The file name.
     */
    public String getFileName(){
        return FILE_NAME;
    }


}
