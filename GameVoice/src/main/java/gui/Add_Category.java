package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * The Add_Category class represents the GUI for adding a new category.
 */
public class Add_Category extends Application {
    /**
     * Starts the application for adding a new category.
     * @param primaryStage The primary stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GameVoice");
        // Create the grid pane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Labels for the text fields
        Label categoryLabel = new Label("Category:");
        GridPane.setConstraints(categoryLabel, 0, 0);

        Label descriptionLabel = new Label("Description:");
        GridPane.setConstraints(descriptionLabel, 0, 1);

        Label gamesLabel = new Label("Games:");
        GridPane.setConstraints(gamesLabel, 0, 2);

        // TextAreas for category, description, and games
        TextArea categoryTextArea = new TextArea();
        categoryTextArea.setPrefRowCount(1);
        categoryTextArea.setPromptText("Enter name of the Category");
        GridPane.setConstraints(categoryTextArea, 1, 0);

        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.setPrefRowCount(3);
        descriptionTextArea.setPromptText("Enter description to the Category");
        GridPane.setConstraints(descriptionTextArea, 1, 1);

        TextArea gamesTextArea = new TextArea();
        gamesTextArea.setPrefRowCount(3);
        gamesTextArea.setPromptText("Enter games separated by commas (,)");
        GridPane.setConstraints(gamesTextArea, 1, 2);

        // Save Button to save added data
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            saveData(categoryTextArea.getText(), descriptionTextArea.getText(), gamesTextArea.getText());
            clearFields(categoryTextArea, descriptionTextArea, gamesTextArea);
        });
        GridPane.setConstraints(saveButton, 0, 3);

        // Back Button to return to the admin menu
        Button BackToAdminMenuButton = new Button("Back Menu");
        BackToAdminMenuButton.setOnAction(event -> {
            clearFields(categoryTextArea, descriptionTextArea, gamesTextArea);
            Program prog = new Program();
            try {
                primaryStage.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        GridPane.setConstraints(BackToAdminMenuButton, 0, 4);

        gridPane.getChildren().addAll(categoryLabel, descriptionLabel, gamesLabel,
                categoryTextArea, descriptionTextArea, gamesTextArea, saveButton, BackToAdminMenuButton);

        Scene scene = new Scene(gridPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Saves the entered data for a new category to a file.
     * @param category The category name.
     * @param description The category description.
     * @param games The games associated with the category.
     */
    private void saveData(String category, String description, String games) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/Data/awards.txt", true))) {
            writer.write(category);
            writer.newLine();
            writer.write(description);
            writer.newLine();
            // Split games by comma and write each game on a new line
            String[] gamesArray = games.split(",");
            for (String game : gamesArray) {
                writer.write(game.trim()); // Remove leading and trailing spaces
                writer.newLine();
            }
            writer.newLine(); // Add an empty line to separate entries
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Clears the specified text areas.
     * @param textAreas The text areas to clear.
     */
    private void clearFields(TextArea... textAreas) {
        for (TextArea textArea : textAreas) {
            textArea.clear();
        }
    }
    /**
     * The main method of the Add_Category class.
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
