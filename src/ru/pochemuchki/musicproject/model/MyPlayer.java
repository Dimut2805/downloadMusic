package ru.pochemuchki.musicproject.model;

import javafx.scene.image.Image;
import ru.pochemuchki.musicproject.JobWithSource;
import ru.pochemuchki.musicproject.abstractclasses.TrackAbstract;
import ru.pochemuchki.musicproject.constains.Constains;

import java.io.File;
import java.net.MalformedURLException;

public class MyPlayer extends TrackAbstract implements Constains {
    private JobWithSource jobWithSource;

    public MyPlayer() {
        jobWithSource = new JobWithSource();
    }
    /**
     * Базовые настройки плеера
     */
    public void setBaseSettingPlayer() {
        getName().setText("Не выбрано");
        try {
            getIcon().setImage(new Image(new File(BASE_IMAGE_JPG_NOT_FOUND_MUSIC).toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        jobWithSource.pausePlayer();
    }

    public void on() {
        jobWithSource.startPlayer(getName().getText());
    }

    public void off() {
        jobWithSource.stopPlayer();
    }
    public void close() {}
}