package ru.pochemuchki.musicproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI implements Constains {
    Stage window;

    GUI(Stage stage) {
        this.window = stage;
    }

    public void createGUI() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
            window.setScene(new Scene(root));
            window.setOnCloseRequest(event -> System.exit(0));
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}