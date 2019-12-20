package ru.pochemuchki.musicproject.model;

import javafx.scene.text.Text;

abstract class ButtonAbstract {
    private Text text;
    private int numberInList;

    ButtonAbstract(Text text, int numberInList) {
        this.text = text;
        this.numberInList = numberInList;
    }

    abstract void click();
}