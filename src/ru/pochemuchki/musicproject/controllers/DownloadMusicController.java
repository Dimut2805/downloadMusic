package ru.pochemuchki.musicproject.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ru.pochemuchki.musicproject.DownloadsSource;
import ru.pochemuchki.musicproject.constains.Constains;
import ru.pochemuchki.musicproject.objects.AttributesMusic;
import ru.pochemuchki.musicproject.utils.FindAttributeMusic;

import java.io.IOException;
import java.util.List;

public class DownloadMusicController implements Constains {
    DownloadsSource download = new DownloadsSource();
    MyMusicController myMusicController;
    @FXML
    VBox vboxContentDownloadScrollPane;
    @FXML
    String urlSection;
    @FXML
    public ComboBox<String> sections;

    /**
     * Добавление стороннего контролера в this
     *
     * @param myMusicController сторонний контроллер
     */
    public void setMyMusicController(MyMusicController myMusicController) {
        this.myMusicController = myMusicController;
    }

    /**
     * Скачивание музыки с сайта
     *
     * @param attributes паспортный данные музыки
     */
    private void downloadMusicButton(AttributesMusic attributes) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                download.downloadSong(attributes.getUrlMusic(), attributes.getAuthor() + " - " + attributes.getNameMusic());
                try {
                    download.downloadImage(attributes.getUrlImage(), attributes.getAuthor() + " - " + attributes.getNameMusic());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myMusicController.startUpdatePathMusic();
                return null;
            }
        };
        new Thread(task).start();
    }

    /**
     * Получение разделов сайта
     */
    @FXML
    private void getComboBox() {
        urlSection = HASH_MAP_SECTIONS.get(sections.getValue());
    }

    /**
     * Поиск музыки в разделе сайта
     */
    @FXML
    private void clickFindMusicOnSite() {
        if (vboxContentDownloadScrollPane.getChildren().size() != 0) {
            vboxContentDownloadScrollPane.getChildren().clear();
        }
        List<AttributesMusic> attributeMusic = FindAttributeMusic.findAttributesMusic(urlSection);
        int numberMusic = 1;
        for (AttributesMusic attributes : attributeMusic) {
            Label nameMusic = new Label(numberMusic + ". " + attributes.getAuthor() + " - " + attributes.getNameMusic()) {{
                setFont(Font.font(15));
            }};
            Button buttonDownload = new Button("Cкачать") {{
                setOnAction(event -> downloadMusicButton(attributes));
            }};
            vboxContentDownloadScrollPane.getChildren().add(new HBox(10, nameMusic, buttonDownload));
            numberMusic++;
        }
    }
}