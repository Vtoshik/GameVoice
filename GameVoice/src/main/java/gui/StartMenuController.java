package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.CustomExceptionHandler;
import main.IDandPassword;
import main.MainLogic;
/**
 * The StartMenuController class controls the behavior of the start menu GUI.
 */
public class StartMenuController {
    /**
     * The IDandPassword object for user authentication.
     */
    private IDandPassword idAndPassword;
    /**
     * The label for displaying information on the start menu.
     */
    @FXML
    private Label start_menu_label1;
    /**
     * The label for displaying information on the start menu.
     */
    @FXML
    private Label start_menu_label2;
    /**
     * The login button on the start menu.
     */
    @FXML
    private Button start_menu_login;
    /**
     * The register button on the start menu.
     */
    @FXML
    private Button start_menu_register;
    /**
     * The MainLogic object for managing application logic.
     */
    private MainLogic mainLogic = MainLogic.getInstance();;
    /**
     * Constructs a new StartMenuController.
     * @throws CustomExceptionHandler If an error occurs during initialization.
     */
    public StartMenuController() throws CustomExceptionHandler {
    }
    /**
     * Sets the IDandPassword object for user authentication.
     * @param idAndPassword The IDandPassword object.
     */
    public void setIdAndPassword(IDandPassword idAndPassword) {
        this.idAndPassword = idAndPassword;
    }
    /**
     * Handles the action when the login button is clicked.
     * @param event The action event.
     * @throws Exception If an error occurs during login.
     */
    @FXML
    protected void startMenuLoginButtonAction(ActionEvent event) throws Exception {
        mainLogic.loadUserDataFromFile("src/main/Data/programdata.txt");
        //mainLogic.registerAdmin("admin", "admin");
        Program prog = new Program();
        prog.changeScene("hello-view.fxml");

    }
    /**
     * Handles the action when the register button is clicked.
     * @param event The action event.
     * @throws Exception If an error occurs during registration.
     */
    @FXML
    protected void startMenuRegisterButtonAction(ActionEvent event) throws Exception {
        //mainLogic.registerAdmin("admin", "admin");
        mainLogic.loadUserDataFromFile("src/main/Data/programdata.txt");
        Program prog = new Program();
        prog.changeScene("register-menu.fxml");
    }

}
