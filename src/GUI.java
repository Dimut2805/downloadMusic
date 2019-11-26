import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI {

    String title;
    int width, height;

    GUI(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void createGUI(Stage stage, Scene scene) {
        stage.setTitle(title);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setScene(scene);
        stage.show();
    }
}