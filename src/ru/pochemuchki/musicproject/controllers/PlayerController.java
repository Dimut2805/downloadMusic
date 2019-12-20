package ru.pochemuchki.musicproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ru.pochemuchki.musicproject.constains.Constains;
import ru.pochemuchki.musicproject.model.MyPlayer;

/**
 * Контроллер работы с  музыкой
 */
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

    /**
     * Нажатие на паузу в плеере
     */
    @FXML
    private void pauseMusicButton() {
        listenMusicButton.setDisable(false);
        stopMusicButton.setDisable(false);
        pauseMusicButton.setDisable(true);
        deleteFromPlayerButton.setDisable(false);
        myPlayer.pause();
    }

    /**
     * Получение плеера из главного контроллера
     *
     * @param myPlayer плеер
     */
    void setMyPlayer(MyPlayer myPlayer) {
        this.myPlayer = myPlayer;
    }

    /**
     * Нажатие на стоп в плеере
     */
    @FXML
    void stopMusicButton() {
        listenMusicButton.setDisable(false);
        stopMusicButton.setDisable(false);
        pauseMusicButton.setDisable(false);
        deleteFromPlayerButton.setDisable(false);
        myPlayer.off();
    }

    /**
     * Нажатие на пуск в плеере
     */
    @FXML
    private void listenMusicButton() {
        listenMusicButton.setDisable(true);
        stopMusicButton.setDisable(false);
        pauseMusicButton.setDisable(false);
        deleteFromPlayerButton.setDisable(false);
        myPlayer.on();
    }

    /**
     * Нажатие на удаление музыки из плеера
     */
    @FXML
    private void deleteFromPlayerButton() {
        stopMusicButton.setDisable(true);
        listenMusicButton.setDisable(true);
        pauseMusicButton.setDisable(true);
        deleteFromPlayerButton.setDisable(true);
        myPlayer.setBaseSettingPlayer();
        myPlayer.off();
    }
}