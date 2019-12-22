package ru.pochemuchki.musicproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class MainController {
    @FXML
    DownloadMusicController downloadMusicController;
    @FXML
    PlayerController playerController;
    @FXML
    MyMusicController myMusicController;
    @FXML
    VBox window;

    /**
     * Полготовка приложения к работе
     */
    public void initialize() {
        sendControllersForControllers();
        myMusicController.startUpdatePathMusic();
    }


    private void sendControllersForControllers() {
        downloadMusicController.setMyMusicController(myMusicController);
        myMusicController.setPlayerController(playerController);
    }
}