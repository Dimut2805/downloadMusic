package ru.pochemuchki.musicproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ru.pochemuchki.musicproject.constains.Constains;
import ru.pochemuchki.musicproject.model.AttributesMusic;
import ru.pochemuchki.musicproject.model.DownloadButton;
import ru.pochemuchki.musicproject.utils.FindAttributeMusic;

import java.util.List;

public class DownloadMusicController implements Constains {
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
            DownloadButton downloadButton = new DownloadButton("Скачать", attributes);
            vboxContentDownloadScrollPane
                    .getChildren()
                    .add(new HBox(10,
                            nameMusic, downloadButton.getButton()));
            numberMusic++;
            myMusicController.startUpdatePathMusic();
        }
    }
}