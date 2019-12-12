package ru.pochemuchki.musicproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ru.pochemuchki.musicproject.utils.BaseOperation;


public class MainController {
    @FXML
    PlayerController playerController;
    @FXML
    MyMusicController myMusicController;
    @FXML
    VBox window;

    public void initialize() {
        myMusicController.setPlayerController(playerController);
        myMusicController.startUpdatePathMusic();
    }
}