package ru.pochemuchki.musicproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ru.pochemuchki.musicproject.objects.MyPlayer;


public class MainController {
    @FXML
    DownloadMusicController downloadMusicController;
    @FXML
    PlayerController playerController;
    @FXML
    MyMusicController myMusicController;
    @FXML
    VBox window;

    public void initialize() {
        MyPlayer myPlayer = new MyPlayer();
        myPlayer.setLabelMusic(playerController.nameMusicAtPlayer);
        myPlayer.setImageViewMusic(playerController.imagePlayer);
        downloadMusicController.setMyMusicController(myMusicController);
        myMusicController.setMyPlayer(myPlayer);
        playerController.setMyPlayer(myPlayer);
        myMusicController.setPlayerController(playerController);
        myMusicController.startUpdatePathMusic();
    }
}