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
        mediafile = new Media(new File(PATH_MUSICS + nameFile).toURI().toString());
        mediaplayer = new MediaPlayer(mediafile);
            if (mediaplayer.getStatus() == MediaPlayer.Status.PAUSED) {
                mediaplayer.setStartTime(CurrentTime);
                mediaplayer.play();
            }
            if (mediaplayer.getStatus() == MediaPlayer.Status.STOPPED) {
                mediaplayer.stop();
                mediaplayer.setStartTime(Duration.ZERO);
                mediaplayer.play();
            }
            else{
                mediaplayer.play();
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
    public void removeImage(String nameImg) {
        File imageFile = new File(PATH_IMAGE + "\\" + nameImg);
        imageFile.delete();
    }
}
