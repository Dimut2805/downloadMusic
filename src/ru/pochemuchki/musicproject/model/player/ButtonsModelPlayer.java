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
        stopMusicButton = new StopMusicButton(buttons.getStopMusicButton(), myPlayer, this);
        pauseMusicButton = new PauseMusicButton(buttons.getPauseMusicButton(), myPlayer, this);
        startMusicButton = new StartMusicButton(buttons.getListenMusicButton(), myPlayer, this);
        closeMusicButton = new CloseMusicButton(buttons.getDeleteFromPlayerButton(), myPlayer, this);
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

    void clickStop() {
        getStopMusicButton().getButton().setDisable(true);
        getStartMusicButton().getButton().setDisable(false);
        getPauseMusicButton().getButton().setDisable(true);
        getCloseMusicButton().getButton().setDisable(false);
    }

    public void clickAdd() {
        getStopMusicButton().getButton().setDisable(true);
        getStartMusicButton().getButton().setDisable(false);
        getPauseMusicButton().getButton().setDisable(true);
        getCloseMusicButton().getButton().setDisable(false);
    }

    void clickStart() {
        getStopMusicButton().getButton().setDisable(false);
        getStartMusicButton().getButton().setDisable(true);
        getPauseMusicButton().getButton().setDisable(false);
        getCloseMusicButton().getButton().setDisable(true);
    }

    void clickClose() {
        getStopMusicButton().getButton().setDisable(true);
        getStartMusicButton().getButton().setDisable(true);
        getPauseMusicButton().getButton().setDisable(true);
        getCloseMusicButton().getButton().setDisable(true);
    }

    void clickPause() {
        getStopMusicButton().getButton().setDisable(false);
        getStartMusicButton().getButton().setDisable(false);
        getPauseMusicButton().getButton().setDisable(true);
        getCloseMusicButton().getButton().setDisable(false);
    }
}