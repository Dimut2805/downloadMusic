package ru.pochemuchki.musicproject.model.player;

import javafx.scene.control.Button;
import ru.pochemuchki.musicproject.model.MyPlayer;

public class CloseMusicButton extends JobButtonPlayer {

    CloseMusicButton(Button button, MyPlayer myPlayer, ButtonsModelPlayer buttonsModelPlayer) {
        super(button, myPlayer, buttonsModelPlayer);
    }

    @Override
    public void click() {
        getMyPlayer().close();
        getButtonsModelPlayer().clickClose();
    }
}
