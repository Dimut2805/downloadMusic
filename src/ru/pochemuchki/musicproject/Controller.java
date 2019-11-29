package ru.pochemuchki.musicproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class Controller implements Constains {
    @FXML
    VBox vboxContentPathMusic;
    @FXML
    VBox vboxContentDownloadScrollPane;
    @FXML
    private ComboBox<String> sections;
    @FXML
    String urlSection;

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
        new Song().downloadSong(attributes[2], attributes[1]);
        updatePathMusic();
    }

    @FXML
    public void updatePathMusic() {
        if (vboxContentPathMusic.getChildren().size() != 0) {
            vboxContentPathMusic.getChildren().clear();
        }
        ArrayList<String> musicsPath = DirMyMusic.getMusics();
        int numberMusic = 1;
        for (String music : musicsPath) {
            Text nameMusic = new Text(numberMusic + ". " + music) {{
                setFont(Font.font(15));
            }};
            Button buttonListenMusic = new Button("Слушать") {{
                setOnAction(event -> listenMusicButton(music));
            }};
            Button buttonDeleteMusic = new Button("Удалить") {{
                setOnAction(event -> deleteMusicButton(music));
            }};
            vboxContentPathMusic.getChildren().add(new HBox(10, nameMusic, buttonListenMusic, buttonDeleteMusic));
            numberMusic++;
        }
    }

    private void listenMusicButton(String nameMusic) {
        new Song().playSong(nameMusic);
    }

    private void deleteMusicButton(String music) {
        DirMyMusic.deleteMusic(music);
        updatePathMusic();
    }

    @FXML
    private void getComboBox() {
        urlSection = HASH_MAP_SITE_TABS.get(sections.getValue());
    }

    @FXML
    public void initialize() {
        updatePathMusic();
    }
}