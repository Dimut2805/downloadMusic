package ru.pochemuchki.musicproject;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ExitController {
    @FXML private AnchorPane window;
    @FXML
    private void clickYes() {
        System.exit(0);
    }
    @FXML
    private void clickNo() {
        ((Stage) window.getScene().getWindow()).close();
    }
    @FXML
    public void initialize() {

    }
}
