package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * The LoginApplication class represents the entry point for the login application.
 */
public class LoginApplication extends Application {
    /**
     * Starts the login application by loading the login view.
     * @param stage The primary stage of the application.
     * @throws IOException If an error occurs during loading.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("GameVoice");
        stage.setScene(scene);

        // Pass the loginInfo to the controller
        LoginController controller = fxmlLoader.getController();

        stage.show();
    }
    /**
     * The main entry point of the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}