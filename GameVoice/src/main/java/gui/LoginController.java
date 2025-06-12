package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.CustomExceptionHandler;
import main.MainLogic;
/**
 * The LoginController class controls the behavior of the login GUI.
 */
public class LoginController {
    /**
     * The MainLogic object for managing application logic.
     */
    private MainLogic mainLogic = new MainLogic();
    /**
     * The text field for entering the username.
     */
    @FXML
    private TextField username;
    /**
     * The password field for entering the password.
     */
    @FXML
    private PasswordField password;
    /**
     * The label for displaying login results.
     */
    @FXML
    private Label resultLabel;
    /**
     * The login button on the login interface.
     */
    @FXML
    private Button Login;
    /**
     * The button for navigating back to the main menu.
     */
    @FXML
    private Button BackToMain;
    /**
     * Constructs a new LoginController.
     * @throws CustomExceptionHandler If an error occurs during initialization.
     */
    public LoginController() throws CustomExceptionHandler {
    }
    /**
     * Handles the action when the login button is clicked.
     * @param event The action event.
     * @throws Exception If an error occurs during login.
     */
    @FXML
    protected void handleLoginButtonAction(ActionEvent event) throws Exception {
        mainLogic = MainLogic.getInstance();
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();
        mainLogic.setUsers(username.getText());
        if(enteredUsername.equals("admin") && enteredPassword.equals("admin")){
            Program prog = new Program();
            prog.switchToAdminMenu();
        }else if (mainLogic.getIdAndPassword().isUserValid(enteredUsername, enteredPassword)) {
            switchToGameAwardsInterface();
        } else {
            resultLabel.setText("Login failed!");
        }
    }
    /**
     * Handles the action when the back to main menu button is clicked.
     * @param event The action event.
     * @throws Exception If an error occurs during navigation.
     */
    @FXML
    protected void BackToTheMainmenu(ActionEvent event) throws Exception {
        Program prog = new Program();
        prog.changeScene("startmenu.fxml");
    }
    /**
     * Switches to the game awards interface.
     * @throws Exception If an error occurs during switching.
     */
    @FXML
    private void switchToGameAwardsInterface() throws Exception {
        // Load the FXML file for the game awards interface
        Program prog = new Program();
        if (!mainLogic.getVotingIsOver()){
            prog.switchToVotingScene();
        } else {
            prog.switchToResultScene();
        }
    }
}