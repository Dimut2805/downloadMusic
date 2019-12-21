package ru.pochemuchki.musicproject.abstractclasses;

import javafx.scene.layout.HBox;

public abstract class AttributesObjectMusicAbstract {
    private HBox objectHBox;

    public void setObjectHBox(HBox objectHBox) {
        this.objectHBox = objectHBox;
    }

    public HBox getObjectHBox() {
        return objectHBox;
    }
}
