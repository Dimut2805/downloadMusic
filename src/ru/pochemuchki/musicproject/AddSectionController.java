package ru.pochemuchki.musicproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class AddSectionController {
    Controller mainController;
    @FXML
    private AnchorPane window;
    @FXML
    private TextField nameNewSection;
    @FXML
    private TextField pageURL;
    @FXML
    private Label labelError;
    private void smartUpdateSections() {
        //mainController.sections.setItems(mainController.sections);
    }
    @FXML
    private void clickAddSection() {
    }

    @FXML
    public void initialize() {
        FXMLLoader mainLayout = new FXMLLoader(getClass().getResource("Layout.fxml"));

        try {
            Node layout1 = (Node) mainLayout.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainController = mainLayout.getController();
    }
}
