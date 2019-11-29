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
            Scene scene = new Scene(root);
            scene.getStylesheets().add(String.valueOf(this.getClass().getResource("stylesheet.css")));
            window.setTitle("Приложение для скачивания музыки.");
            window.setScene(scene);
            window.setOnCloseRequest(event -> System.exit(0));
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}