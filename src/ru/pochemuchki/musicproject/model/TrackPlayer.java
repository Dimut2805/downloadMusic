package ru.pochemuchki.musicproject.model;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TrackPlayer {
    private ImageView icon;
    private Label name;
    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public ImageView getIcon() {
        return icon;
    }

    public Label getName() {
        return name;
    }
}
