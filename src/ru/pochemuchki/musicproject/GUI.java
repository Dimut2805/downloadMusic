package ru.pochemuchki.musicproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private String siteUrl;
    VBox vboxContentPathMusic;
    String title;
    int width, height;
    Stage stage;

    GUI(Stage stage, String title, int width, int height) {
        this.stage = stage;
        this.title = title;
        this.width = width;
        this.height = height;
        vboxContentPathMusic = new VBox();
    }

    public void createGUI() {
        stage.setTitle(title);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setScene(getMyScene());
        stage.show();
    }
    public void updatePathMusic() {
        if(vboxContentPathMusic.getChildren().size()!=0) {
            vboxContentPathMusic.getChildren().clear();
        }
        vboxContentPathMusic.getChildren().add(fillPathMusic());
    }
    public VBox fillPathMusic() {
        Directory directory = new Directory(PATH_MUSICS);
        return new VBox(10) {
            {
                ArrayList<String> musicsPath = directory.getMusics();
                int numberMusic = 1;
                for (String elements : musicsPath) {
                    Text nameMusic = new Text(numberMusic + ". " + elements) {{
                        setFont(Font.font(15));
                    }};
                    Button buttonListenMusic = new Button("Слушать");
                    Button buttonDeleteMusic = new Button("Удалить") {{
                        setOnAction(event -> directory.deleteMusic(elements));
                    }};
                    getChildren().add(new HBox(10, nameMusic, buttonListenMusic, buttonDeleteMusic));
                    numberMusic++;
                }
            }
        };
    }
    private Scene getMyScene() {
        Text textSections = new Text("Разделы") {
            {
                setFont(Font.font(24));
            }
        };
        ComboBox<String> comboBox = new ComboBox<String>(OBSERVABLE_LIST_SITE_TABS) {{
            setValue("Не выбрано");
            setOnAction(actionEvent -> siteUrl = (HASH_MAP_SITE_TABS.get(getValue())));
        }};
        updatePathMusic();
        ScrollPane scrollPanePath = new ScrollPane(vboxContentPathMusic) {
            {
                setPrefViewportHeight(350);
                setPrefViewportWidth(500);
            }
        };
        VBox vboxContentDownloadScrollPane = new VBox(10) {{

        }};
        ScrollPane scrollPaneMusic = new ScrollPane(vboxContentDownloadScrollPane) {
            {
                setPrefViewportHeight(350);
                setPrefViewportWidth(600);
            }
        };
        Button buttonFindMusic = new Button("Найти") {{
            setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (vboxContentDownloadScrollPane.getChildren().size() != 0) {
                        vboxContentDownloadScrollPane.getChildren().clear();
                    }
                    ArrayList<String[]> attributeMusic = DownloadUrl.findAttributeMusic(siteUrl);
                    int numberMusic = 1;
                    for (String[] elements : attributeMusic) {
                        Text nameMusic = new Text(numberMusic + ". " + elements[0] + " - " + elements[1]) {{
                            setFont(Font.font(15));
                        }};
                        Song song = new Song();
                        Button buttonDownload = new Button("Cкачать") {{
                            setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    song.downloadSong(elements[2], elements[1]);
                                }
                            });
                        }};
                        vboxContentDownloadScrollPane.getChildren().add(new HBox(10, nameMusic, buttonDownload));
                        numberMusic++;
                    }
                }
            });
        }};
        VBox masterPathBox = new VBox(new Label("Моя Музыка"), scrollPanePath);
        HBox hboxSearchBySections = new HBox(textSections, comboBox, buttonFindMusic);
        VBox leftVBox = new VBox(hboxSearchBySections, scrollPaneMusic);
        FlowPane rootNode = new FlowPane(leftVBox, masterPathBox);
        return new Scene(rootNode);
    }
}