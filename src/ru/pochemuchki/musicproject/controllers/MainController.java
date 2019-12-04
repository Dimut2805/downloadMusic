package ru.pochemuchki.musicproject.controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.pochemuchki.musicproject.*;
import ru.pochemuchki.musicproject.objects.AttributesMusic;
import ru.pochemuchki.musicproject.objects.GUI;
import ru.pochemuchki.musicproject.objects.MyPlayer;
import ru.pochemuchki.musicproject.utils.BaseOperation;
import ru.pochemuchki.musicproject.utils.FindAttributeMusic;
import ru.pochemuchki.musicproject.utils.NetworkUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class MainController extends BaseOperation implements NetworkUtils {
    MyPlayer myPlayer;
    Integer intNumberListenMusicButton;
    Button listenMusicOn;
    @FXML
    Button stopMusicButton;
    @FXML
    Button deleteFromPlayerButton;
    @FXML
    Button listenMusicButton;
    @FXML
    ImageView imagePlayer;
    @FXML
    Label nameMusicAtPlayer;
    @FXML
    VBox window;
    @FXML
    VBox vboxContentPathMusic;
    @FXML
    VBox vboxContentDownloadScrollPane;
    @FXML
    public ComboBox<String> sections;
    @FXML
    String urlSection;

    @FXML
    private void clickExit() {
        GUI.exitGUI();
    }

    @FXML
    private void clickFindMusicOnSite() {
        if (vboxContentDownloadScrollPane.getChildren().size() != 0) {
            vboxContentDownloadScrollPane.getChildren().clear();
        }
        ArrayList<AttributesMusic> attributeMusic = FindAttributeMusic.findAttributesMusic(urlSection);
        int numberMusic = 1;
        for (AttributesMusic attributes : attributeMusic) {
            Text nameMusic = new Text(numberMusic + ". " + attributes.getAuthor() + " - " + attributes.getNameMusic()) {{
                setFont(Font.font(15));
            }};
            Button buttonDownload = new Button("Cкачать") {{
                setOnAction(event -> downloadMusicButton(attributes));
            }};
            vboxContentDownloadScrollPane.getChildren().add(new HBox(10, nameMusic, buttonDownload));
            numberMusic++;
        }
    }


    private void downloadMusicButton(AttributesMusic attributes) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                new Song().downloadSong(attributes.getUrlMusic(), attributes.getAuthor() + " - " + attributes.getNameMusic());
                try {
                    new Image().downloadImage(attributes.getUrlImage(), attributes.getAuthor() + " - " + attributes.getNameMusic());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startUpdatePathMusic();
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    public void startUpdatePathMusic() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updatePathMusic();
            }
        });
    }

    private void updatePathMusic() {
        if (vboxContentPathMusic.getChildren().size() != 0) {
            vboxContentPathMusic.getChildren().clear();
        }
        ArrayList<String> musicsPath = getMusics();
        int numberMusic = 1;
        for (String music : musicsPath) {
            Label nameMusic = new Label(numberMusic + ". " + music) {{
                setFont(Font.font(15));
            }};
            ImageView imageView = null;
            try {
                imageView = new ImageView(new javafx.scene.image.Image(fileIconPlayer(music).toURI().toURL().toString()));
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            int finalNumberMusic = numberMusic;
            Button buttonListenMusic = new Button("Слушать") {{
                setOnAction(event -> addMusicInPlayer(music, ((Button) ((HBox) vboxContentPathMusic.getChildren().get(finalNumberMusic - 1)).getChildren().get(2)), finalNumberMusic));
            }};
            Button buttonDeleteMusic = new Button("Удалить") {{
                setOnAction(event -> deleteMusicButton(music));
            }};
            vboxContentPathMusic.getChildren().add(new HBox(10, imageView, nameMusic, buttonListenMusic, buttonDeleteMusic));
            numberMusic++;
        }
    }

    private File fileIconPlayer(String nameMusic) {
        String namePicture = nameMusic.substring(0, nameMusic.length() - 4);
        return findObjectInDir(BASE_DIR_DOWNLOADER_MUSIC_PICTURE + namePicture) ?
                new File(PATH_IMAGE + "\\DownloaderMusicPicture\\" + namePicture) :
                new File(PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\DedaultTrackIcon.jpg");
    }

    private void addMusicInPlayer(String nameMusic, Button button, int numberMusic) {
        if (intNumberListenMusicButton != null) {
            ((Button) ((HBox) vboxContentPathMusic.getChildren().get(intNumberListenMusicButton - 1)).getChildren().get(2)).setDisable(false);
        }
        button.setDisable(true);
        intNumberListenMusicButton = numberMusic;
        listenMusicButton.setDisable(false);
        deleteFromPlayerButton.setDisable(false);
        myPlayer.setNameMusic(new Label(nameMusic));
        nameMusicAtPlayer.setText(myPlayer.getNameMusic().getText());
        myPlayer.setIconMusic(new ImageView(new javafx.scene.image.Image(createFileUrlString(fileIconPlayer(nameMusic)))));
        imagePlayer.setImage(myPlayer.getIconMusic().getImage());
    }

    @FXML
    private void listenMusicButton() {
        listenMusicButton.setDisable(true);
        stopMusicButton.setDisable(false);
        deleteFromPlayerButton.setDisable(true);
        myPlayer.on();
    }

    @FXML
    private void deleteFromPlayerButton() {
        myPlayer.setBaseSettingPlayer();
        stopMusicButton.setDisable(true);
        listenMusicButton.setDisable(true);
        deleteFromPlayerButton.setDisable(true);
        imagePlayer.setImage(myPlayer.getIconMusic().getImage());
        nameMusicAtPlayer.setText(myPlayer.getNameMusic().getText());
    }

    @FXML
    private void stopMusicButton() {
        listenMusicButton.setDisable(false);
        deleteFromPlayerButton.setDisable(false);
    }

    private void deleteMusicButton(String music) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                deleteMusic(music);
                new Image().removeImage(music);
                startUpdatePathMusic();
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    private void getComboBox() {
        urlSection = HASH_MAP_SECTIONS.get(sections.getValue());
    }

    @FXML
    public void initialize() {
        listenMusicOn = new Button();
        myPlayer = new MyPlayer();
        imagePlayer.setImage(
                new javafx.scene.image.Image(
                        createFileUrlString(new File(BASE_IMAGE_JPG_NOT_FOUND_MUSIC))));
        startUpdatePathMusic();
    }
}