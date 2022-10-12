package com.example.poemreadergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WordCounterApp extends Application {
    Stage window;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        Parent rootFrame = loader.load();
        window = primaryStage;
        window.setScene(new Scene(rootFrame, 605, 400));
        window.setTitle("Word Counter");
        window.show();
    }

    public static void main(String[] args) { launch();
    }
}