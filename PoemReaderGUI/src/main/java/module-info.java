/** *This is the container for the word counter/frequency application */
module com.example.poemreadergui {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.example.poemreadergui to javafx.fxml;
    exports com.example.poemreadergui;
}