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

import static ru.pochemuchki.musicproject.JobWithSource.removeImage;
import static ru.pochemuchki.musicproject.utils.DirectoryUtils.deleteMusic;

public class MyMusicController implements Constains {
    MyPlayer myPlayer;
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
     * Получить плеер из главного контроллера
     *
     * @param myPlayer плеер
     */
    public void setMyPlayer(MyPlayer myPlayer) {
        this.myPlayer = myPlayer;
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
     * Добавление музыки в плеер
     *
     * @param nameMusic   название музыки
     * @param button      добавления музыки в плеер
     * @param numberMusic номер музыки в директории пользователя
     */
    private void addMusicInPlayer(String nameMusic, Button button, int numberMusic) {
        if (intNumberListenMusicButton != null) {
            (((HBox) vboxContentPathMusic.getChildren().get(intNumberListenMusicButton - 1)).getChildren().get(2)).setDisable(false);
            myPlayer.off();
        }
        button.setDisable(true);
        intNumberListenMusicButton = numberMusic;
        playerController.listenMusicButton.setDisable(false);
        playerController.deleteFromPlayerButton.setDisable(false);
        playerController.pauseMusicButton.setDisable(true);
        playerController.stopMusicButton.setDisable(true);
        myPlayer.getName().setText(nameMusic);
        try {
            myPlayer.getIcon().setImage(new Image(fileIconPlayer(nameMusic).toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаление музыки
     *
     * @param music название музыки
     */
    private void deleteMusicButton(String music) {
        String namePicture = music.substring(0, music.length() - 4);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                deleteMusic(music);
                removeImage(namePicture);
                startUpdatePathMusic();
                return null;
            }
        };
        new Thread(task).start();
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
                    new AttributesPathMyMusicModel(track, new Track(myPlayer.getName(), myPlayer.getIcon()));
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