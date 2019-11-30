package ru.pochemuchki.musicproject;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
            window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    event.consume();
                    exitGUI();
                }
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exitGUI() {
        Parent root = null;
        try {
            root = FXMLLoader.load(GUI.class.getResource("Exit.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(GUI.class.getResource("stylesheet.css")));
        Stage window = new Stage();
        window.setTitle("Выход");
        window.setOnCloseRequest(event -> window.close());
        window.setScene(scene);
        window.show();
    }
}