package ru.pochemuchki.musicproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import ru.pochemuchki.musicproject.constains.Constains;
import ru.pochemuchki.musicproject.model.downloadmusic.AttributesDownloadMusicModel;
import ru.pochemuchki.musicproject.model.AttributesMusic;
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
        fillObjects();
    }

    private void fillObjects() {
        List<AttributesMusic> attributeMusic = FindAttributeMusic.findAttributesMusic(urlSection);
        for (AttributesMusic attributes : attributeMusic) {
            AttributesDownloadMusicModel attributesDownloadMusicModel = new AttributesDownloadMusicModel(attributes);
            vboxContentDownloadScrollPane
                    .getChildren()
                    .add(attributesDownloadMusicModel.getObjectHBox());
            myMusicController.startUpdatePathMusic();
        }
    }
}