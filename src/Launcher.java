import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Launcher extends Application implements Constains {
    private String siteUrl;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            Text text = new Text("Разделы");
            text.setFont(Font.font(24));
            ObservableList<String> observableList = FXCollections.observableArrayList("Не выбрано", "Топ-100",
                    "Русский поп", "Русский рок", "Дабстэп");
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("Не выбрано", null);
            hashMap.put("Топ-100", "https://muzika.vip");
            hashMap.put("Русский поп", "https://muzika.vip/m-genres/русский-поп-7071");
            hashMap.put("Русский рок", "https://muzika.vip/m-genres/русский-рок-7070");
            hashMap.put("Дабстэп", "https://muzika.vip/m-genres/dubstep-7043");
            ComboBox<String> comboBox = new ComboBox<>(observableList);
            comboBox.setValue("Не выбрано");
            comboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    siteUrl = (hashMap.get(comboBox.getValue()));
                }
            });
            Button button = new Button("Найти");
            VBox musicBox = new VBox(10);
            ScrollPane scrollPanePath = new ScrollPane();
            scrollPanePath.setPrefViewportHeight(350);
            scrollPanePath.setPrefViewportWidth(500);
            ScrollPane scrollPaneMusic = new ScrollPane(musicBox);
            scrollPaneMusic.setPrefViewportHeight(350);
            scrollPaneMusic.setPrefViewportWidth(500);
            HBox urlSiteBox = new HBox(text, comboBox, button);
            VBox masterMusicBox = new VBox(urlSiteBox, scrollPaneMusic);
            VBox masterPathBox = new VBox(new Label("Директория скачанной музыки"), scrollPanePath);
            FlowPane masterNode = new FlowPane(masterMusicBox, masterPathBox);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (musicBox.getChildren().size() != 0) {
                        musicBox.getChildren().clear();
                    }
                    ArrayList<String[]> arrayList = DownloadUrl.findAttributeMusic(siteUrl);
                    int i = 1;
                    for (String[] element : arrayList) {
                        Text text1 = new Text(i+". "+element[0]+" - "+element[1]);
                        text1.setFont(Font.font(15));
                        musicBox.getChildren().add(new HBox(text1, new Button("Cкачать")));
                        i++;
                    }
                }
            });
            Scene scene = new Scene(masterNode);
            stage.setScene(scene);
            stage.setTitle("Downloader music (muzika.vip)");
            stage.setWidth(1300);
            stage.setHeight(500);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}