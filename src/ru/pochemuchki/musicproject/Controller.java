package ru.pochemuchki.musicproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private void clickExit() {
        Stage stage = new Stage();
        Button yes = new Button("Да");
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        Button no = new Button("Нет");
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        HBox hBox = new HBox(5, yes, no);
        VBox vBox = new VBox(5, new Label("Вы точно хотите выйти?"), hBox);
        stage.setTitle("Выход");
        stage.setWidth(200);
        stage.setHeight(200);
        stage.setOnCloseRequest(event -> stage.close());
        stage.setScene(new Scene(vBox));
        stage.show();
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