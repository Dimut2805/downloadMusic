import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;

public class Launcher extends Application implements Constains {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            Text text = new Text();
            TextField urlSiteTextField = new TextField();
            Label urlSiteLabel = new Label(SERVER);
            Button button = new Button("Найти");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    HashMap<String,String> hashMap = DownloadUrl.findAttributeMusic(SERVER + urlSiteTextField.getText());
                    text.setText("Можно скачать: "+hashMap.get("author")+" - "+hashMap.get("nameMusic"));
                }
            });
            FlowPane urlSiteNode = new FlowPane(urlSiteLabel, urlSiteTextField);
            FlowPane masterNode = new FlowPane(Orientation.VERTICAL, urlSiteNode, text, button);
            Scene scene = new Scene(masterNode);
            stage.setScene(scene);
            stage.setTitle("Downloader music (muzika.vip)");
            stage.setWidth(400);
            stage.setHeight(400);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}