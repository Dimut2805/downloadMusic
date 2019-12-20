package ru.pochemuchki.musicproject;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.pochemuchki.musicproject.constains.Constains;
import ru.pochemuchki.musicproject.objects.GUI;

public class Launcher extends Application implements Constains {

    public static void main(String[] args) {

        Application.launch(args);
    }

    /**
     * Запуск приложения
     *
     * @param window главное окно
     */
    @Override
    public void start(Stage window) {
        new GUI(window).createGUI();
    }
}