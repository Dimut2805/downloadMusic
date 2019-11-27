package ru.pochemuchki.musicproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application implements Constains {

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