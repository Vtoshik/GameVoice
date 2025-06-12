module com.example.gamevoice {
    requires javafx.controls;
    requires javafx.fxml;


    opens gui to javafx.fxml;
    exports gui;
    exports main;
    opens main to javafx.fxml;
}