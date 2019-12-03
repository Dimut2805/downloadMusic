package ru.pochemuchki.musicproject;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;

public class MyPlayer implements Constains {
    ImageView iconMusic;
    Label nameMusic;

    MyPlayer() {
        try {
            iconMusic = new ImageView(new Image(new File(PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\кот.jpg").toURI().toURL().toString()));
            nameMusic.setText("Не выбрано");
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
}