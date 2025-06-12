package main;

import java.io.File;
/**
 * The CustomExceptionHandler class extends Exception and provides custom exception handling functionality.
 */
public class CustomExceptionHandler extends Exception {
    /**
     * Constructs a new CustomExceptionHandler with the specified error message.
     * @param message The error message.
     */
    public CustomExceptionHandler(String message) {
        super(message);
    }
    /**
     * Handles file-related exceptions by checking if the file exists.
     * @param file The file to be checked.
     * @throws CustomExceptionHandler If the file does not exist.
     */
    public static void handleFileException(File file) throws CustomExceptionHandler {
        if (!file.exists()) {
            throw new CustomExceptionHandler("Critical error: File does not exist - " + file.getAbsolutePath());
        }
    }
}
