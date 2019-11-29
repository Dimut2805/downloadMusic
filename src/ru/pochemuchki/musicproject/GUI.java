package ru.pochemuchki.musicproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI implements Constains {
    Stage stage;

    GUI(Stage stage) {
        this.stage = stage;
    }

    public void createGUI() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(event -> System.exit(0));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}