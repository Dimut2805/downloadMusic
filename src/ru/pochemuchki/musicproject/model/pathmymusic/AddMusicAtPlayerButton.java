package ru.pochemuchki.musicproject.model.pathmymusic;


import ru.pochemuchki.musicproject.abstractclasses.ButtonAbstract;
import ru.pochemuchki.musicproject.model.Track;
import ru.pochemuchki.musicproject.model.player.ButtonsModelPlayer;

class AddMusicAtPlayerButton extends ButtonAbstract {
    private Track trackObject;
    private ButtonsModelPlayer buttonsModelPlayer;

    AddMusicAtPlayerButton(String nameButton, Track trackObject, ButtonsModelPlayer buttonsModelPlayer) {
        super(nameButton);
        this.trackObject = trackObject;
        this.buttonsModelPlayer = buttonsModelPlayer;
    }


    @Override
    public void click() {
        buttonsModelPlayer.getMyPlayer().getIcon().setImage(trackObject.getIcon().getImage());
        buttonsModelPlayer.getMyPlayer().getName().setText(trackObject.getName().getText());
        buttonsModelPlayer.clickAdd();
    }
}