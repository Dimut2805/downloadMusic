package ru.pochemuchki.musicproject.model.pathmymusic;

import javafx.scene.layout.HBox;
import ru.pochemuchki.musicproject.abstractclasses.AttributesObjectMusicAbstract;
import ru.pochemuchki.musicproject.model.Track;
import ru.pochemuchki.musicproject.model.player.ButtonsModelPlayer;

public class AttributesPathMyMusicModel extends AttributesObjectMusicAbstract {

    public AttributesPathMyMusicModel(Track trackObject, ButtonsModelPlayer buttonsModelPlayer) {
        setObjectHBox(new HBox(
                trackObject.getIcon(),
                trackObject.getName(),
                new AddMusicAtPlayerButton("Слушать", trackObject, buttonsModelPlayer).getButton(),
                new DeleteMusicAtPlayerButton("Удалить", trackObject).getButton()));
    }
}