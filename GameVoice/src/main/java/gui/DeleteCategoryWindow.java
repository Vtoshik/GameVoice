package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * The DeleteCategoryWindow class represents the GUI for deleting a category.
 */
public class DeleteCategoryWindow extends Application {
    /**
     * Define the file path for the awards data file
     */
    private static final String FILE_NAME = "src/main/Data/awards.txt";
    /**
     * Instantiate the MainLogic object to handle category deletion
     */
    private MainLogic mainlogic = new MainLogic();
    /**
     * Constructor for the DeleteCategoryWindow class.
     * Throws CustomExceptionHandler if an error occurs during initialization.
     */
    public DeleteCategoryWindow() throws CustomExceptionHandler {
    }
    /**
     * Starts the application for deleting a category.
     * @param primaryStage The primary stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Delete Category");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            // Read each line from the awards data file
            while ((line = br.readLine()) != null) {
                // Check if the line is not empty
                if (!line.isEmpty()) {
                    // Add label for category name
                    Label categoryLabel = new Label(line);

                    // Create delete button for each category
                    Button deleteButton = new Button("Delete");
                    deleteButton.setOnAction(event -> {
                        // Get the category name from the label and delete it
                        String categoryName = categoryLabel.getText();
                        mainlogic.deleteCategory(categoryName);

                        // Remove label and delete button from the layout
                        layout.getChildren().removeAll(categoryLabel, deleteButton);
                    });

                    // Add label and delete button to the layout
                    layout.getChildren().addAll(categoryLabel, deleteButton);

                    // Skip description
                    br.readLine();

                    // Skip game lines
                    while (!(line = br.readLine()).isEmpty()) {
                        // Skip game lines
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Encapsulate the layout within a scroll pane
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        // Create the scene and set it to the stage
        Scene scene = new Scene(scrollPane, 400, 300);
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
