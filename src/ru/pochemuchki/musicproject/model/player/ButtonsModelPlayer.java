package ru.pochemuchki.musicproject.model.player;

import ru.pochemuchki.musicproject.model.MyPlayer;
import ru.pochemuchki.musicproject.model.Track;

public class ButtonsModelPlayer {
    private MyPlayer myPlayer;
    private StopMusicButton stopMusicButton;
    private PauseMusicButton pauseMusicButton;
    private StartMusicButton startMusicButton;
    private CloseMusicButton closeMusicButton;

    public ButtonsModelPlayer(ButtonsPlayerObject buttons, Track trackPlayer) {
        myPlayer = new MyPlayer(trackPlayer.getName(), trackPlayer.getIcon());
        stopMusicButton = new StopMusicButton(buttons.getStopMusicButton(), myPlayer);
        pauseMusicButton = new PauseMusicButton(buttons.getPauseMusicButton(), myPlayer);
        startMusicButton = new StartMusicButton(buttons.getListenMusicButton(), myPlayer);
        closeMusicButton = new CloseMusicButton(buttons.getDeleteFromPlayerButton(), myPlayer);
    }

    public StopMusicButton getStopMusicButton() {
        return stopMusicButton;
    }

    public PauseMusicButton getPauseMusicButton() {
        return pauseMusicButton;
    }

    public CloseMusicButton getCloseMusicButton() {
        return closeMusicButton;
    }

    public StartMusicButton getStartMusicButton() {
        return startMusicButton;
    }

    public MyPlayer getMyPlayer() {
        return myPlayer;
    }
}