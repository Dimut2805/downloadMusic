package ru.pochemuchki.musicproject;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class MyPlayer implements Constains {
    ImageView iconMusic;
    Label nameMusic;

    MyPlayer() {
        nameMusic = new Label("Не выбрано");
        try {
            iconMusic = new ImageView(new Image(new File(PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\notFoundMusic.jpg").toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setBaseSettingPlayer() {
        nameMusic = new Label("Не выбрано");
        try {
            iconMusic = new ImageView(new Image(new File(PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\notFoundMusic.jpg").toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Label getNameMusic() {
        return nameMusic;
    }

    public ImageView getIconMusic() {
        return iconMusic;
    }

    public void setNameMusic(Label nameMusic) {
        this.nameMusic = nameMusic;
    }

    public void setIconMusic(ImageView iconMusic) {
        this.iconMusic = iconMusic;
    }

    public void on() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws FileNotFoundException {
                new Song().jobWithSong(nameMusic.getText(), "start");
                return null;
            }
        };
        new Thread(task).start();
    }

    public void off() {
    }
}