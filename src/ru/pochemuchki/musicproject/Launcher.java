package ru.pochemuchki.musicproject;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.pochemuchki.musicproject.constains.Constains;

public class Launcher extends Application implements Constains {

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage window) {
        new ru.pochemuchki.musicproject.GUI(window).createGUI();
    }
}