package ru.pochemuchki.musicproject.model.pathmymusic;

import javafx.scene.layout.HBox;
import ru.pochemuchki.musicproject.model.Track;

public class AttributesPathMyMusicModel {
    private Track trackObject;
    private AddMusicAtPlayerButton addMusicModel;
    private DeleteMusicAtPlayerButton deleteMusicModel;
    private HBox objectHBox;

    public AttributesPathMyMusicModel(Track trackObject, Track trackPlayer) {
        this.trackObject = trackObject;
        addMusicModel = new AddMusicAtPlayerButton("Слушать", trackObject, trackPlayer);
        deleteMusicModel = new DeleteMusicAtPlayerButton("Удалить", trackObject);
        objectHBox = new HBox(
                trackObject.getIcon(),
                trackObject.getName(),
                addMusicModel.getButton(),
                deleteMusicModel.getButton());
    }

    public HBox getObject() {
        return objectHBox;
    }
}