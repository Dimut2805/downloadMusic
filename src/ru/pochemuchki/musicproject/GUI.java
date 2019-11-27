package ru.pochemuchki.musicproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI implements Constains {
    private String urlSection;
    private VBox vboxContentPathMusic = new VBox();
    private VBox vboxContentDownloadScrollPane = new VBox();
    private Directory directory = new Directory(PATH_MUSICS);
    Stage stage;
    String title;
    int width, height;

    GUI(Stage stage, String title, int width, int height) {
        this.stage = stage;
        this.title = title;
        this.width = width;
        this.height = height;
        vboxContentDownloadScrollPane = new VBox();
        vboxContentPathMusic = new VBox();
    }

    public void createGUI() {
        stage.setTitle(title);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setScene(createRootScene());
        stage.setOnCloseRequest(event -> System.exit(0));
        stage.show();
    }

    public void fillPathMusic() {
        if (vboxContentPathMusic.getChildren().size() != 0) {
            vboxContentPathMusic.getChildren().clear();
        }
        ArrayList<String> musicsPath = directory.getMusics();
        int numberMusic = 1;
        for (String music : musicsPath) {
            Text nameMusic = new Text(numberMusic + ". " + music) {{
                setFont(Font.font(15));
            }};
            Button buttonListenMusic = new Button("Слушать") {{
                setOnAction(event -> listenMusicButton());
            }};
            Button buttonDeleteMusic = new Button("Удалить") {{
                setOnAction(event -> deleteMusicButton(music));
            }};
            vboxContentPathMusic.getChildren().add(new HBox(10, nameMusic, buttonListenMusic, buttonDeleteMusic));
            numberMusic++;
        }
    }

    private Scene createRootScene() {
        FlowPane rootNode = new FlowPane(createLeftScene(), createRightScene());
        return new Scene(rootNode);
    }

    private VBox createRightScene() {
        ScrollPane scrollPaneRightScene = new ScrollPane(vboxContentPathMusic) {
            {
                setPrefViewportHeight(350);
                setPrefViewportWidth(500);
            }
        };
        return new VBox(new Label("Моя Музыка"), scrollPaneRightScene);
    }

    private VBox createLeftScene() {
        Text textSections = new Text("Разделы") {
            {
                setFont(Font.font(24));
            }
        };
        ComboBox<String> sectionsSiteComboBox = new ComboBox<String>(OBSERVABLE_LIST_SITE_TABS) {{
            setValue("Не выбрано");
            setOnAction(actionEvent -> urlSection = (HASH_MAP_SITE_TABS.get(getValue())));
        }};
        ScrollPane scrollPaneLeftScene = new ScrollPane(vboxContentDownloadScrollPane) {
            {
                setPrefViewportHeight(350);
                setPrefViewportWidth(600);
            }
        };
        Button buttonFindMusic = new Button("Найти") {{
            setOnAction(event -> findMusicOnSiteButton());
        }};
        HBox hboxSearchBySections = new HBox(textSections, sectionsSiteComboBox, buttonFindMusic);
        return new VBox(hboxSearchBySections, scrollPaneLeftScene);
    }

    private void listenMusicButton() {

    }

    private void deleteMusicButton(String music) {
        directory.deleteMusic(music);
    }

    private void findMusicOnSiteButton() {
        if (vboxContentDownloadScrollPane.getChildren().size() != 0) {
            vboxContentDownloadScrollPane.getChildren().clear();
        }
        ArrayList<String[]> attributeMusic = DownloadUrl.findAttributeMusic(urlSection);
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
    }
}