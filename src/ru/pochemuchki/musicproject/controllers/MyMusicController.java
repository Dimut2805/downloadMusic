package ru.pochemuchki.musicproject.controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ru.pochemuchki.musicproject.constains.Constains;
import ru.pochemuchki.musicproject.model.*;
import ru.pochemuchki.musicproject.model.pathmymusic.AttributesPathMyMusicModel;
import ru.pochemuchki.musicproject.utils.DirectoryUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class MyMusicController implements Constains {
    Integer intNumberListenMusicButton;
    @FXML
    PlayerController playerController;
    @FXML
    VBox vboxContentPathMusic;

    /**
     * Запуск обновления директории скачанной музыкой
     */
    @FXML
    public void startUpdatePathMusic() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updatePathMusic();
            }
        });
    }

    /**
     * Получение ссылки на действующий сторонний контроллер
     *
     * @param playerController сторонний контроллер
     */
    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    /**
     * Иконка для плеера
     *
     * @param nameMusic названия музыки
     * @return иконка
     */
    private File fileIconPlayer(String nameMusic) {
        String namePicture = nameMusic.substring(0, nameMusic.length() - 4);
        return DirectoryUtils.findObjectInDir(BASE_DIR_DOWNLOADER_MUSIC_PICTURE + namePicture) ?
                new File(BASE_DIR_DOWNLOADER_MUSIC_PICTURE + namePicture) :
                new File(BASE_IMAGE_JPG_BASE_ICON);
    }

    /**
     * Обновление директории пользователя
     */
    private void updatePathMusic() {
        if (vboxContentPathMusic.getChildren().size() != 0) {
            vboxContentPathMusic.getChildren().clear();
        }
        fillObjects();
    }

    private void fillObjects() {
        ArrayList<String> musicsPath = DirectoryUtils.getMusics();
        int numberMusic = 1;
        for (String music : musicsPath) {
            Track track = createTrack(music);
            AttributesPathMyMusicModel modelTrackPathMusic =
                    new AttributesPathMyMusicModel(track,
                            new Track(playerController.buttonsModelPlayer.getMyPlayer().getName(),
                                    playerController.buttonsModelPlayer.getMyPlayer().getIcon()));
            vboxContentPathMusic
                    .getChildren()
                    .add(modelTrackPathMusic.getObjectHBox());
            numberMusic++;
        }

    }

    private Track createTrack(String music) {
        Label nameMusic = new Label(music) {{
            setFont(Font.font(15));
        }};
        ImageView imageView = null;
        try {
            imageView = new ImageView(new Image(fileIconPlayer(music).toURI().toURL().toString())) {{
                setFitWidth(50);
                setFitHeight(50);
            }};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new Track(nameMusic, imageView);
    }
}