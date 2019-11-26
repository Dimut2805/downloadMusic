package ru.pochemuchki.musicproject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Launcher extends Application implements Constains {
    private String siteUrl;
    Song sound = new Song();

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        try {
            GUI rootGUI = new GUI(stage, "Downloader music (muzika.vip)", 1300, 500);
            UpdatePathMusicThread thread = new UpdatePathMusicThread(rootGUI);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}