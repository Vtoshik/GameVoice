package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import main.CustomExceptionHandler;
import main.MainLogic;
import java.util.Map;
/**
 * This class represents the application for displaying voting results.
 */
public class VotingResults extends Application {
    private MainLogic mainLogic;
    /**
     * Overrides the start method from the Application class to initialize the voting results interface.
     * @param primaryStage The primary stage for displaying the voting results interface.
     * @throws CustomExceptionHandler If an exception occurs during initialization.
     */
    @Override
    public void start(Stage primaryStage) throws CustomExceptionHandler {
        primaryStage.setTitle("Voting Results");

        // Initialize MainLogic instance
        mainLogic = MainLogic.getInstance();

        // Compute winning games
        Map<String, String> winningGames = mainLogic.computeWinningGames();

        // Create VBox for displaying results
        VBox vbox = new VBox(10);

        // Add labels for each category and its winning game
        for (Map.Entry<String, String> entry : winningGames.entrySet()) {
            String category = entry.getKey();
            String winningGame = entry.getValue();

            // Create label for displaying category and winning game
            Label categoryLabel = new Label("Category: " + category);
            Label winningGameLabel = new Label("Winning Game: " + winningGame);

            // Add labels to the VBox
            vbox.getChildren().addAll(categoryLabel, winningGameLabel, new Line());
        }

        // Create exit button
        Button ExitButton = new Button("Exit");
        ExitButton.setOnAction(event -> {
            Program program = new Program();
            try {
                program.changeScene("startmenu.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Add exit button to the VBox
        vbox.getChildren().add(ExitButton);

        // Create scene with VBox and set it to the primary stage
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * The main method of the VotingResults class.
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
