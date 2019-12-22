package ru.pochemuchki.musicproject.model.player;

import javafx.scene.control.Button;
import ru.pochemuchki.musicproject.abstractclasses.ButtonAbstract;
import ru.pochemuchki.musicproject.model.MyPlayer;

class JobButtonPlayer extends ButtonAbstract {
    private MyPlayer myPlayer;
    private ButtonsModelPlayer buttonsModelPlayer;

    JobButtonPlayer(Button button, MyPlayer myPlayer, ButtonsModelPlayer buttonsModelPlayer) {
        super(button);
        this.myPlayer = myPlayer;
        this.buttonsModelPlayer = buttonsModelPlayer;
        getButton().setDisable(true);
    }

    public MyPlayer getMyPlayer() {
        return myPlayer;
    }

    public ButtonsModelPlayer getButtonsModelPlayer() {
        return buttonsModelPlayer;
    }

    @Override
    public void click() {

    }
}
