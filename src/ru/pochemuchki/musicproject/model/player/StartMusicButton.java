package ru.pochemuchki.musicproject.model.player;

import javafx.scene.control.Button;
import ru.pochemuchki.musicproject.model.MyPlayer;

public class StartMusicButton extends JobButtonPlayer {

    StartMusicButton(Button button, MyPlayer myPlayer) {
        super(button, myPlayer);
        getButton().setDisable(false);
    }

    @Override
    public void click() {
        getButton().setDisable(true);
    }
}
