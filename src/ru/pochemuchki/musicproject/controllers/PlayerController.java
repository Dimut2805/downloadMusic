package ru.pochemuchki.musicproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ru.pochemuchki.musicproject.constains.Constains;
import ru.pochemuchki.musicproject.objects.MyPlayer;

import java.io.File;
import java.net.MalformedURLException;

public class PlayerController implements Constains {
    MyPlayer myPlayer;
    @FXML
    Button stopMusicButton;
    @FXML
    Button deleteFromPlayerButton;
    @FXML
    public Button listenMusicButton;
    @FXML
    ImageView imagePlayer;
    @FXML
    Button pauseMusicButton;
    @FXML
    Label nameMusicAtPlayer;

    @FXML
    private void pauseMusicButton() {
        listenMusicButton.setDisable(false);
        stopMusicButton.setDisable(false);
        pauseMusicButton.setDisable(true);
        deleteFromPlayerButton.setDisable(false);
        myPlayer.pause();
    }
    void setMyPlayer(MyPlayer myPlayer) {
        this.myPlayer = myPlayer;
    }

    @FXML
    private void stopMusicButton() {
        myPlayer.off();
    }

    @FXML
    private void listenMusicButton() {
        listenMusicButton.setDisable(true);
        stopMusicButton.setDisable(false);
        pauseMusicButton.setDisable(false);
        deleteFromPlayerButton.setDisable(false);
        myPlayer.on();
    }

    @FXML
    private void deleteFromPlayerButton() {
        myPlayer.setBaseSettingPlayer();
        stopMusicButton.setDisable(true);
        listenMusicButton.setDisable(true);
        pauseMusicButton.setDisable(true);
        deleteFromPlayerButton.setDisable(true);
        myPlayer.setBaseSettingPlayer();
        myPlayer.off();
    }
}