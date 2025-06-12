package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.CustomExceptionHandler;
import main.IDandPassword;
import main.MainLogic;
import java.io.File;
/**
 * The RegisterController class controls the behavior of the register menu GUI.
 */
public class RegisterController {
    /**
     * The reference to the main program.
     */
    protected Program program;
    /**
     * The MainLogic object for managing application logic.
     */
    private MainLogic mainLogic = MainLogic.getInstance();
    /**
     * The register button on the register menu.
     */
    @FXML
    private Button registerButton;
    /**
     * The text field for entering the username.
     */
    @FXML
    private TextField regUsername;
    /**
     * The password field for entering the password.
     */
    @FXML
    private PasswordField regPassword;
    /**
     * The label for displaying registration messages.
     */
    @FXML
    private Label regLabel;
    /**
     * The button for navigating back to the main menu.
     */
    @FXML
    private Button BackToMain;
    /**
     * Constructs a new RegisterController.
     * @throws CustomExceptionHandler If an error occurs during initialization.
     */
    public RegisterController() throws CustomExceptionHandler {
    }
    /**
     * Handles the action when the register button is clicked.
     * @param event The action event.
     * @throws Exception If an error occurs during registration.
     */
    @FXML
    protected void handleRegisterButtonAction(ActionEvent event) throws Exception {
        String enteredUsername = regUsername.getText();
        String enteredPassword = regPassword.getText();

        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            regLabel.setText("Please enter username and password");
            return;
        }

        if (mainLogic.getIdAndPassword().isUserExists(enteredUsername)) {
            regLabel.setText("Username already exists");
            return;
        }

        mainLogic.getIdAndPassword().writeNewUser(enteredUsername, enteredPassword);
        mainLogic.registerUser(enteredUsername, enteredPassword);
        mainLogic.writeUser(enteredUsername, enteredPassword);
        program = new Program();
        program.changeScene("hello-view.fxml");
    }
    /**
     * Handles the action when the back to main menu button is clicked.
     * @param event The action event.
     * @throws Exception If an error occurs during navigation.
     */
    @FXML
    protected void BackToTheMainMenu(ActionEvent event) throws Exception {
        Program prog = new Program();
        prog.changeScene("startmenu.fxml");
    }
}