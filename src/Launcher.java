import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
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
            FlowPane flowPane = new FlowPane();
            FlowPane urlSiteNode = new FlowPane(urlSiteLabel, urlSiteTextField);
            FlowPane masterNode = new FlowPane(Orientation.VERTICAL, urlSiteNode, text, button, flowPane);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ArrayList<String[]> arrayList = DownloadUrl.findAttributeMusic(SERVER+urlSiteTextField.getText());
                    for (String[] element : arrayList) {
                        flowPane.getChildren().add(new Label(element[0]));
                    };
                }
            });
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