package ru.pochemuchki.musicproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ru.pochemuchki.musicproject.model.MyPlayer;


public class MainController {
    private MyPlayer myPlayer;
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