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
        createPlayer();
        sendPlayerForControllers();
        sendControllersForControllers();
        myMusicController.startUpdatePathMusic();
    }

    private void createPlayer() {
        myPlayer = new MyPlayer();
        myPlayer.getTrackPlayer().setName(playerController.nameMusicAtPlayer);
        myPlayer.getTrackPlayer().setIcon(playerController.imagePlayer);
        myPlayer.setBaseSettingPlayer();
    }

    private void sendPlayerForControllers() {
        myMusicController.setMyPlayer(myPlayer);
        playerController.setMyPlayer(myPlayer);
    }

    private void sendControllersForControllers() {
        downloadMusicController.setMyMusicController(myMusicController);
        myMusicController.setPlayerController(playerController);
    }
}