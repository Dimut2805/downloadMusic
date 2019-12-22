package ru.pochemuchki.musicproject.model.player;

import javafx.scene.control.Button;
import ru.pochemuchki.musicproject.model.MyPlayer;

public class PauseMusicButton extends JobButtonPlayer {

    PauseMusicButton(Button button, MyPlayer myPlayer, ButtonsModelPlayer buttonsModelPlayer) {
        super(button, myPlayer, buttonsModelPlayer);
    }

    @Override
    public void click() {
        getMyPlayer().pause();
        getButtonsModelPlayer().clickPause();
    }
}