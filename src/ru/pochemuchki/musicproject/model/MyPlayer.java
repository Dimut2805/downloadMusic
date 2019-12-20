package ru.pochemuchki.musicproject.model;

import javafx.scene.image.Image;
import ru.pochemuchki.musicproject.JobWithSource;
import ru.pochemuchki.musicproject.constains.Constains;
import ru.pochemuchki.musicproject.model.TrackPlayer;

import java.io.File;
import java.net.MalformedURLException;

public class MyPlayer implements Constains {
    private JobWithSource jobWithSource;
    private TrackPlayer trackPlayer;

    public MyPlayer() {
        jobWithSource = new JobWithSource();
        trackPlayer = new TrackPlayer();
    }

    public TrackPlayer getTrackPlayer() {
        return trackPlayer;
    }

    /**
     * Базовые настройки плеера
     */
    public void setBaseSettingPlayer() {
        trackPlayer.getName().setText("Не выбрано");
        try {
            trackPlayer.getIcon().setImage(new Image(new File(BASE_IMAGE_JPG_NOT_FOUND_MUSIC).toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        jobWithSource.pausePlayer();
    }

    public void on() {
        jobWithSource.startPlayer(trackPlayer.getName().getText());
    }

    public void off() {
        jobWithSource.stopPlayer();
    }
}