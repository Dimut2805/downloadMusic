package ru.pochemuchki.musicproject.model.pathmymusic;

import javafx.concurrent.Task;
import ru.pochemuchki.musicproject.JobWithSource;
import ru.pochemuchki.musicproject.abstractclasses.ButtonAbstract;
import ru.pochemuchki.musicproject.model.Track;
import ru.pochemuchki.musicproject.utils.DirectoryUtils;

class DeleteMusicAtPlayerButton extends ButtonAbstract {
    private Track trackObject;

    DeleteMusicAtPlayerButton(String nameButton, Track trackObject) {
        super(nameButton);
        this.trackObject = trackObject;
    }

    private String deleteFormatString(String music) {
        return music.substring(0, music.length() - 4);
    }

    @Override
    public void click() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                DirectoryUtils.deleteMusic(trackObject.getName().getText());
                JobWithSource.removeImage(deleteFormatString(trackObject.getName().getText()));
                return null;
            }
        };
        new Thread(task).start();
    }
}