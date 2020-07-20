package com.kacper.zielinski.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// TODO add README.MD, that requires youtube-dl and future
// TODO add license to github
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO put it somewhere else (constants)
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        primaryStage.setTitle("YouTubeDG 0.1.1");
        primaryStage.setScene(new Scene(root, 640, 380));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
