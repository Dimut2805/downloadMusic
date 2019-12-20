package ru.pochemuchki.musicproject.model;


abstract class ButtonAbstract{
    private String nameButton;

    ButtonAbstract(String nameButton) {
        this.nameButton = nameButton;
    }

    abstract void click();
}