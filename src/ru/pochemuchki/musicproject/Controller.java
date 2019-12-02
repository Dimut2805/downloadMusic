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

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Controller implements Constains {
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
                new Image().downloadImage(attributes[3], attributes[0] + " - " + attributes[1]);
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
            Text nameMusic = new Text(numberMusic + ". " + music) {{
                setFont(Font.font(15));
            }};
            String namePicture = music.substring(0, music.length() - 4) + ".jpg";
            ImageView imageView = null;
            if (DirMyMusic.findPicture(namePicture)) {
                File file = new File(PATH_IMAGE + "\\DownloaderMusicPicture\\" + namePicture);
                try {
                    imageView = new ImageView(new javafx.scene.image.Image(file.toURI().toURL().toString()));
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            Button buttonListenMusic = new Button("Слушать") {{
                setOnAction(event -> addMusicInPlayer(music));
            }};
            Button buttonDeleteMusic = new Button("Удалить") {{
                setOnAction(event -> deleteMusicButton(music));
            }};
            if (imageView == null) {
                File file = new File(PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\кот.jpg");
                try {
                    imageView = new ImageView(new javafx.scene.image.Image(file.toURI().toURL().toString()));
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            vboxContentPathMusic.getChildren().add(new HBox(10, imageView, nameMusic, buttonListenMusic, buttonDeleteMusic));
            numberMusic++;
        }
    }

    private void addMusicInPlayer(String nameMusic) {
        String namePicture = nameMusic.substring(0, nameMusic.length() - 4) + ".jpg";
        File file = new File(PATH_IMAGE + "\\DownloaderMusicPicture\\"+namePicture);
        nameMusicAtPlayer.setText(nameMusic);
        try {
            imagePlayer.setImage(new javafx.scene.image.Image(file.toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void listenMusicButton() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws FileNotFoundException {
                new Song().playSong(nameMusicAtPlayer.getText());
                return null;
            }
        };
        new Thread(task).start();
    }

    private void deleteMusicButton(String music) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                DirMyMusic.deleteMusic(music);
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
        File file = new File(PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\кот.jpg");
        try {
            imagePlayer.setImage(new javafx.scene.image.Image(file.toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (!DirMyMusic.findDir("DownloaderMusicPicture")) {
            new File(PATH_IMAGE, "DownloaderMusicPicture").mkdir();
        }
        startUpdatePathMusic();
    }
}