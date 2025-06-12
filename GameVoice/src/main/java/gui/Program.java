package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The Program class represents the main entry point of the application.
 */
public class Program extends Application {
    /**
     * The primary stage of the application.
     */
    private static Stage stg;
    /**
     * Starts the application by loading the start menu.
     * @param primaryStage The primary stage of the application.
     * @throws Exception If an error occurs during loading.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("startmenu.fxml"));
        primaryStage.setTitle("GameVoice");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
    /**
     * Switches to the voting scene.
     * @throws Exception If an error occurs during switching.
     */
    public void switchToVotingScene() throws Exception {
        VotingApplication test = new VotingApplication();
        test.start(stg);
    }
    /**
     * Switches to the admin menu.
     * @throws Exception If an error occurs during switching.
     */
    public void switchToAdminMenu() throws Exception {
        AdminPageApplication admin_page = new AdminPageApplication();
        admin_page.start(stg);
    }
    /**
     * Switches to the voting results scene.
     * @throws Exception If an error occurs during switching.
     */
    public void switchToResultScene() throws Exception {
        VotingResults result_page = new VotingResults();
        result_page.start(stg);
    }
    /**
     * Navigates back to the start menu.
     * @throws Exception If an error occurs during navigation.
     */
    public void BackToStartMenu() throws Exception {
        stg.getScene().setRoot(FXMLLoader.load(getClass().getResource("startmenu.fxml")));
    }
    /**
     * Changes the scene to the specified FXML file.
     * @param fxml The path of the FXML file.
     * @throws Exception If an error occurs during scene change.
     */
    public void changeScene(String fxml) throws Exception {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }
    /**
     * The main entry point of the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
