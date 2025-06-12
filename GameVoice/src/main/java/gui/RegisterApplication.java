package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * The RegisterApplication class represents the entry point for the register application.
 */
public class RegisterApplication extends Application {
    /**
     * Starts the register application by loading the register menu.
     * @param stage The primary stage of the application.
     * @throws IOException If an error occurs during loading.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterApplication.class.getResource("register-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("GameVoice");
        stage.setScene(scene);
        RegisterController controller = fxmlLoader.getController();
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