package ru.pochemuchki.musicproject.model.pathmymusic;

import javafx.scene.layout.HBox;
import ru.pochemuchki.musicproject.abstractclasses.AttributesObjectMusicAbstract;
import ru.pochemuchki.musicproject.model.Track;

public class AttributesPathMyMusicModel extends AttributesObjectMusicAbstract {

    public AttributesPathMyMusicModel(Track trackObject, Track trackPlayer) {
        setObjectHBox(new HBox(
                trackObject.getIcon(),
                trackObject.getName(),
                new AddMusicAtPlayerButton("Слушать", trackObject, trackPlayer).getButton(),
                new DeleteMusicAtPlayerButton("Удалить", trackObject).getButton()));
    }
}