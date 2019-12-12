package ru.pochemuchki.musicproject.objects;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ru.pochemuchki.musicproject.JobWithSource;
import ru.pochemuchki.musicproject.constains.Constains;

import java.io.File;
import java.net.MalformedURLException;

public class MyPlayer implements Constains {
    private JobWithSource jobWithSource;
    private ImageView iconMusic;
    private Label nameMusic;

    public MyPlayer() {
        jobWithSource = new JobWithSource();
        try {
            iconMusic = new ImageView(new Image(new File(PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\BaseIconNotFoundMusic.jpg").toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setBaseSettingPlayer() {
        nameMusic.setText("Не выбрано");
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

    public void setLabelMusic(Label nameMusic) {
        this.nameMusic = nameMusic;
    }
   public void setImageViewMusic(ImageView iconMusic) {
        this.iconMusic = iconMusic;
    }

    public void setNameMusic(String nameMusic) {
        this.nameMusic.setText(nameMusic);
    }

    public void setIconMusic(Image image) {
        iconMusic.setImage(image);
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