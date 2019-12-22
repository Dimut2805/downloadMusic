package ru.pochemuchki.musicproject.model.player;

import javafx.scene.control.Button;
import ru.pochemuchki.musicproject.model.MyPlayer;

public class StartMusicButton extends JobButtonPlayer {

    StartMusicButton(Button button, MyPlayer myPlayer, ButtonsModelPlayer buttonsModelPlayer) {
        super(button, myPlayer, buttonsModelPlayer);
    }

    @Override
    public void click() {
        getMyPlayer().on();
        getButtonsModelPlayer().clickStart();
    }
}
