package ru.pochemuchki.musicproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application implements Constains {

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage window) {
        new GUI(window).createGUI();
    }
}