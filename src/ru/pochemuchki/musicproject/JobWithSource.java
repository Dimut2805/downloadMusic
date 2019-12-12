package ru.pochemuchki.musicproject;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import ru.pochemuchki.musicproject.constains.Constains;

import java.io.File;

/**
 * Класс для работы с ресурсами
 * Методы :
 * startPlay - Предназначен для воспроизведения плеера
 * pausePlayer - Предназначен для паузы плеера
 * stopPlay - Предназначен для остановки плеера
 * removeImage - предназначен для удаления картинок
 */
public class JobWithSource implements Constains {
    Media mediafile = null;
    MediaPlayer mediaplayer = null;
    Duration CurrentTime = null;


    /**
     * метод воспроизведения звукового файла
     */

    public void startPlayer(String nameFile) {
        if (mediaplayer == null) {
            mediafile = new Media(new File(PATH_MUSICS + nameFile).toURI().toString());
            mediaplayer = new MediaPlayer(mediafile);
            mediaplayer.play();
        } else {
            if (mediaplayer.getStatus() == MediaPlayer.Status.PAUSED) {
                mediaplayer.setStartTime(CurrentTime);
                mediaplayer.play();
            }
            if (mediaplayer.getStatus() == MediaPlayer.Status.STOPPED) {
                mediaplayer.stop();
                mediaplayer.setStartTime(Duration.ZERO);
                mediaplayer.play();
            }
        }
    }

    /**
     * метод паузы воспроизведения
     */
    public void pausePlayer() {
        mediaplayer.pause();
    }

    /**
     * метод остановки плеера
     */
    public void stopPlayer() {
        if (mediaplayer != null) {
            CurrentTime = Duration.ZERO;
            mediaplayer.setStartTime(Duration.ZERO);
            mediaplayer.stop();
        }
    }

    /**
     * Метод для удаления картинки
     *
     * @param nameImg - имя картики
     */
    public static void removeImage(String nameImg) {
        File imageFile = new File(PATH_IMAGE + "\\" + nameImg);
        imageFile.delete();
    }
}
