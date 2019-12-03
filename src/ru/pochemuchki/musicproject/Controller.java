package ru.pochemuchki.musicproject;

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

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Controller implements Constains {
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
    private void clickDarkStyle() {
        window.getStylesheets().clear();
        window.getStylesheets().add(String.valueOf(this.getClass().getResource("stylesheetDark.css")));
    }

    @FXML
    private void clickWhiteStyle() {
        window.getStylesheets().clear();
        window.getStylesheets().add(String.valueOf(this.getClass().getResource("stylesheetWhite.css")));
    }

    @FXML
    private void clickFindMusicOnSite() {
        if (vboxContentDownloadScrollPane.getChildren().size() != 0) {
            vboxContentDownloadScrollPane.getChildren().clear();
        }
        ArrayList<String[]> attributeMusic = AttributeMusic.findAttributeMusic(urlSection);
        int numberMusic = 1;
        for (String[] attributes : attributeMusic) {
            Text nameMusic = new Text(numberMusic + ". " + attributes[0] + " - " + attributes[1]) {{
                setFont(Font.font(15));
            }};
            Button buttonDownload = new Button("Cкачать") {{
                setOnAction(event -> downloadMusicButton(attributes));
            }};
            vboxContentDownloadScrollPane.getChildren().add(new HBox(10, nameMusic, buttonDownload));
            numberMusic++;
        }
    }


    private void downloadMusicButton(String[] attributes) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                new Song().downloadSong(attributes[2], attributes[0] + " - " + attributes[1]);
                try {
                    new Image().downloadImage(attributes[3], attributes[0] + " - " + attributes[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startUpdatePathMusic();
                return null;
            }
        };
        new Thread(task).start();
    }

    public void startUpdatePathMusic() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updatePathMusic();
            }
        });
    }

    @FXML
    private void updatePathMusic() {
        if (vboxContentPathMusic.getChildren().size() != 0) {
            vboxContentPathMusic.getChildren().clear();
        }
        ArrayList<String> musicsPath = DirMyMusic.getMusics();
        int numberMusic = 1;
        for (String music : musicsPath) {
            Label nameMusic = new Label(numberMusic + ". " + music) {{
                setFont(Font.font(15));
            }};
            ImageView imageView = null;
            try {
                imageView = new ImageView(new javafx.scene.image.Image(iconPlayer(music).toURI().toURL().toString()));
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            int finalNumberMusic = numberMusic;
            Button buttonListenMusic = new Button("Слушать") {{
                setOnAction(event -> addMusicInPlayer(music, ((Button)((HBox)vboxContentPathMusic.getChildren().get(finalNumberMusic-1)).getChildren().get(2)), finalNumberMusic));
            }};
            Button buttonDeleteMusic = new Button("Удалить") {{
                setOnAction(event -> deleteMusicButton(music));
            }};
            vboxContentPathMusic.getChildren().add(new HBox(10, imageView, nameMusic, buttonListenMusic, buttonDeleteMusic));
            numberMusic++;
        }
    }

    private File iconPlayer(String nameMusic) {
        String namePicture = nameMusic.substring(0, nameMusic.length() - 4);
        return DirMyMusic.findPicture("\\DownloaderMusicPicture\\", namePicture) ?
                new File(PATH_IMAGE + "\\DownloaderMusicPicture\\" + namePicture) :
                new File(PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\кот.jpg");
    }
    private void addMusicInPlayer(String nameMusic, Button button, int numberMusic) {
        if (intNumberListenMusicButton!=null) {
            ((Button)((HBox)vboxContentPathMusic.getChildren().get(intNumberListenMusicButton-1)).getChildren().get(2)).setDisable(false);
        }
        button.setDisable(true);
        intNumberListenMusicButton = numberMusic;
        listenMusicButton.setDisable(false);
        deleteFromPlayerButton.setDisable(false);
        myPlayer.setNameMusic(new Label(nameMusic));
        nameMusicAtPlayer.setText(myPlayer.getNameMusic().getText());
        try {
            myPlayer.setIconMusic(new ImageView(new javafx.scene.image.Image(iconPlayer(nameMusic).toURI().toURL().toString())));
            imagePlayer.setImage(myPlayer.getIconMusic().getImage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
        imagePlayer.setImage(myPlayer.iconMusic.getImage());
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
                DirMyMusic.deleteMusic(music);
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

    private void baseOperation() {
        if (!BaseDir.findDir("", "DownloaderMusicPicture")) {
            new File(PATH_IMAGE, "DownloaderMusicPicture").mkdir();
        }
        if (!BaseDir.findDir("\\DownloaderMusicPicture", "basePicture")) {
            new File(PATH_IMAGE + "\\DownloaderMusicPicture", "basePicture").mkdir();
        }
        if (!BaseDir.findPicture("\\DownloaderMusicPicture\\basePicture", "кот.jpg")) {
            try {
                Files.copy(Paths.get("src\\ru\\pochemuchki\\musicproject\\кот.jpg"),
                        Paths.get(BASE_DIR_IMAGE_MUSIC + "\\кот.jpg"),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String createStringURL(String src) {
        try {
            return new File(src).toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }

    @FXML
    public void initialize() {
        baseOperation();
        listenMusicOn = new Button();
        myPlayer = new MyPlayer();
        imagePlayer.setImage(
                new javafx.scene.image.Image(
                        createStringURL(
                                PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\кот.jpg")));
        startUpdatePathMusic();
    }
}