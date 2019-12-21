package ru.pochemuchki.musicproject.model;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ru.pochemuchki.musicproject.abstractclasses.TrackAbstract;

public class Track extends TrackAbstract {
    public Track(Label name) {
        setName(name);
    }
    public Track(Label name, ImageView icon) {
        setName(name);
        setIcon(icon);
    }
}
