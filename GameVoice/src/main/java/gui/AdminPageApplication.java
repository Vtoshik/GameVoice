package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.CustomExceptionHandler;
import main.MainLogic;
/**
 * The AdminPageApplication class represents the GUI for the admin page.
 */
public class AdminPageApplication extends Application {
    private MainLogic mainLogic;
    /**
     * Starts the admin page application.
     * @param primaryStage The primary stage of the application.
     * @throws CustomExceptionHandler If an error occurs during initialization.
     */
    public void start(Stage primaryStage) throws CustomExceptionHandler {
        primaryStage.setTitle("GameVoice");
        // Initialize MainLogic instance
        mainLogic = MainLogic.getInstance();
        // Button to navigate to Add_Category window
        Button addCategoryButton = new Button("Add Category");
        addCategoryButton.setOnAction(event -> {
            // Navigate to Add_Category
            Add_Category addCategory = new Add_Category();
            Stage addCategoryStage = new Stage();
            addCategory.start(addCategoryStage);
        });
        // Open DeleteCategoryWindow
        Button deleteCategoryButton = new Button("Delete Category");
        deleteCategoryButton.setOnAction(event -> {
            DeleteCategoryWindow deleteWindow = null;
            try {
                deleteWindow = new DeleteCategoryWindow();
            } catch (CustomExceptionHandler e) {
                throw new RuntimeException(e);
            }
            Stage deleteCategoryStage = new Stage();
            deleteWindow.start(deleteCategoryStage);
        });
        // Set voting status to finished
        Button FinishVotingButton = new Button("Finish Voting");
        FinishVotingButton.setOnAction(event -> {
            mainLogic.setVotingIsOver(true);
            System.out.println("Voting is Over");
        });
        // Save votes to file (Serialization)
        Button SaveVotesButton = new Button("Save Votes");
        SaveVotesButton.setOnAction(event -> {
            mainLogic.saveVotesToFile("src/main/Data/votes.ser", mainLogic);
        });
        // Load votes from file
        Button LoadVotesButton = new Button("Load Votes");
        LoadVotesButton.setOnAction(event -> {
            mainLogic.loadVotesFromFile("src/main/Data/votes.ser");
        });
        // Clear saved votes file
        Button ClearSavedVotesButton = new Button("Clear Saved Votes");
        ClearSavedVotesButton.setOnAction(event -> {
            mainLogic.clearFile("src/main/Data/votes.ser");
        });
        // Return to the start menu
        Button BackButton = new Button("Exit");
        BackButton.setOnAction(event -> {
            Program program = new Program();
            try {
                program.changeScene("startmenu.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Create layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.getChildren().addAll(addCategoryButton, deleteCategoryButton, FinishVotingButton, SaveVotesButton, LoadVotesButton, ClearSavedVotesButton, BackButton);

        // Set scene
        Scene scene = new Scene(vbox, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * The main entry point of the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
