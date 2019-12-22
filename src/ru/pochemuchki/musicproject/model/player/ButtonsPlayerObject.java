package ru.pochemuchki.musicproject.model.player;

import javafx.scene.control.Button;

public class ButtonsPlayerObject {
    private Button stopMusicButton;
    private Button deleteFromPlayerButton;
    private Button listenMusicButton;
    private Button pauseMusicButton;

    public ButtonsPlayerObject(
            Button stopMusicButton,
            Button deleteFromPlayerButton,
            Button listenMusicButton,
            Button pauseMusicButton) {
        this.deleteFromPlayerButton = deleteFromPlayerButton;
        this.listenMusicButton = listenMusicButton;
        this.pauseMusicButton = pauseMusicButton;
        this.stopMusicButton = stopMusicButton;
    }

    Button getDeleteFromPlayerButton() {
        return deleteFromPlayerButton;
    }

    Button getListenMusicButton() {
        return listenMusicButton;
    }

    Button getStopMusicButton() {
        return stopMusicButton;
    }

    Button getPauseMusicButton() {
        return pauseMusicButton;
    }
}