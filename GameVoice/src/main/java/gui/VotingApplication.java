package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.CustomExceptionHandler;
import main.MainLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represents the application for voting on game categories.
 * Users can select their choices for each category and submit their votes.
 */
public class VotingApplication extends Application {
    /**
     * MainLogic instance to manage voting logic
     */
    private MainLogic mainLogic;
    /**
     * Observer to handle events related to voting
     */
    private Event_follower observer = new Event_follower();
    /**
     * Overrides the start method from the Application class to initialize the voting interface.
     * @param primaryStage The primary stage for displaying the voting interface.
     * @throws CustomExceptionHandler If an exception occurs during initialization.
     */
    @Override
    public void start(Stage primaryStage) throws CustomExceptionHandler {
        primaryStage.setTitle("Game Voice");

        // Create a scroll pane to contain the voting elements
        ScrollPane scrollPane = new ScrollPane();
        VBox vbox = new VBox(10); // VBox to hold the voting elements
        scrollPane.setContent(vbox);
        scrollPane.setFitToWidth(true);

        // Initialize MainLogic instance and attach observer
        mainLogic = MainLogic.getInstance();
        mainLogic.attach(observer);

        try {
            // Read the awards data file
            BufferedReader reader = new BufferedReader(new FileReader("src/main/Data/awards.txt"));
            String line;
            int row = 0;
            // Iterate through each line of the file
            while ((line = reader.readLine()) != null) {
                // Retrieve category name, description, and options
                String category = line;
                String description = reader.readLine();
                List<String> options = new ArrayList<>();
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    options.add(line);
                }

                // Create a VBox to contain the voting elements for each category
                VBox categoryBox = new VBox(10);

                // Display category name
                Label categoryLabel = new Label(category);
                categoryBox.getChildren().add(categoryLabel);

                // Display category description
                Label descriptionLabel = new Label(description);
                descriptionLabel.setWrapText(true);
                descriptionLabel.setMaxWidth(300);
                categoryBox.getChildren().add(descriptionLabel);

                // Create radio buttons for each voting option
                ToggleGroup toggleGroup = new ToggleGroup();
                for (String opt : options) {
                    RadioButton radioButton = new RadioButton(opt);
                    radioButton.setToggleGroup(toggleGroup);
                    categoryBox.getChildren().add(radioButton);
                }

                // Create vote button to submit user's choice
                Button voteButton = new Button("Vote");
                voteButton.setOnAction(event -> {
                    String selectedVote = null;
                    String selectedCategory = category;
                    boolean voted = false;
                    for (Node node : categoryBox.getChildren()) {
                        if (node instanceof RadioButton) {
                            RadioButton radioButton = (RadioButton) node;
                            if (radioButton.isSelected()) {
                                selectedVote = radioButton.getText();
                                voted = true;
                                break;
                            }
                        }
                    }

                    // Check if the user has already voted in this category
                    if (voted) {
                        try {
                            if (MainLogic.getInstance().hasVoted(mainLogic.getUser(), selectedCategory)) {
                                // Update the user's vote in the MainLogic
                                MainLogic.getInstance().updateVote(mainLogic.getUser(), selectedCategory, selectedVote);
                            } else {
                                // Add the user's vote to the MainLogic
                                MainLogic.getInstance().addVote(mainLogic.getUser(), selectedCategory, selectedVote);
                            }
                        } catch (CustomExceptionHandler e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // Notify observers about the vote
                    mainLogic.notifyObservers(selectedCategory, selectedVote);

                    // Further processing if needed
                });
                categoryBox.getChildren().add(voteButton);
                // Add category voting elements to the main VBox
                vbox.getChildren().add(categoryBox);
            }
            // Create an exit button to return to the start menu
            Button exitButton = new Button("Exit");
            exitButton.setOnAction(event -> {
                try {
                    Program program = new Program();
                    mainLogic.deleteobs(observer);
                    program.changeScene("startmenu.fxml");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            // Add the exit button to the main VBox
            vbox.getChildren().add(exitButton);
            // Close the reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the scene with the scroll pane and set it to the primary stage
        Scene scene = new Scene(scrollPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * The main method of the VotingApplication class.
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
