package ru.pochemuchki.musicproject.model.player;

import javafx.scene.control.Button;
import ru.pochemuchki.musicproject.abstractclasses.ButtonAbstract;
import ru.pochemuchki.musicproject.model.MyPlayer;

class JobButtonPlayer extends ButtonAbstract {
    private MyPlayer myPlayer;

    JobButtonPlayer(Button button, MyPlayer myPlayer) {
        super(button);
        this.myPlayer = myPlayer;
    }

    @Override
    public void click() {

    }
}
