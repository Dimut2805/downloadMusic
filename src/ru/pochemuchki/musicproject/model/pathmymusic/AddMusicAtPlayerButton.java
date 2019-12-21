package ru.pochemuchki.musicproject.model.pathmymusic;


import ru.pochemuchki.musicproject.abstractclasses.ButtonAbstract;
import ru.pochemuchki.musicproject.model.Track;

class AddMusicAtPlayerButton extends ButtonAbstract {
    private Track trackObject;
    private Track trackPlayer;

    AddMusicAtPlayerButton(String nameButton, Track trackObject, Track trackPlayer) {
        super(nameButton);
        this.trackObject = trackObject;
        this.trackPlayer = trackPlayer;
    }


    @Override
    public void click() {
        trackPlayer.getIcon().setImage(trackObject.getIcon().getImage());
        trackPlayer.getName().setText(trackObject.getName().getText());
    }
}