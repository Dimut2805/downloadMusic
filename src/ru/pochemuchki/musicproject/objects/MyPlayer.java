package ru.pochemuchki.musicproject.objects;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.pochemuchki.musicproject.JobWithSource;
import ru.pochemuchki.musicproject.constains.Constains;

import java.io.File;
import java.net.MalformedURLException;

public class MyPlayer implements Constains {
    JobWithSource jobWithSource = new JobWithSource();
    private ImageView iconMusic;
    private Label nameMusic;

    public MyPlayer() {
        nameMusic = new Label("Не выбрано");
        try {
            iconMusic = new ImageView(new Image(new File(PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\BaseIconNotFoundMusic.jpg").toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setBaseSettingPlayer() {
        nameMusic = new Label("Не выбрано");
        try {
            iconMusic = new ImageView(new Image(new File(PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\BaseIconNotFoundMusic.jpg").toURI().toURL().toString()));
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

    public void pause() {
        jobWithSource.pausePlayer();
    }

    public void on() {
        jobWithSource.startPlayer(nameMusic.getText());
    }

    public void off() {
        jobWithSource.stopPlayer();
    }
}