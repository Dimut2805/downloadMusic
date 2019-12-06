package ru.pochemuchki.musicproject;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class Song implements Constains {

    Media mediafile = null;
    MediaPlayer mediaplayer = null;
    Duration CurrentTime = null;


    /**
     * метод воспроизведения звукового файла
     */

    public void startPlay(String nameFile) {
        System.out.println(nameFile);
        if(mediaplayer == null) {
            mediafile = new Media(new File(PATH_MUSICS + nameFile).toURI().toString());
            mediaplayer = new MediaPlayer(mediafile);
            mediaplayer.play();
        }
        else{
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
    public void PausePlayer() {
            mediaplayer.pause();
    }

    /**
     * метод остановки плеера
     */
    public void stopPlay() {
        if (mediaplayer != null) {
            CurrentTime = Duration.ZERO;
            mediaplayer.setStartTime(Duration.ZERO);
            mediaplayer.stop();
        }
    }

    /**
     * Метод для скачивания музыки, получает ссылку на скачивание и название файла который скачивается
     *
     * @param src  - сслыка на файл
     * @param name - имя файла
     */
    public void downloadSong(String src, String name) {
        try {
            download(src, name + ".mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для скачивание из интернета.
     *
     * @param src  - ссылка на сайт от куда скачивать
     * @param name - имя которое будет у файла при создание
     * @throws IOException
     */
    public static void download(String src, String name) throws IOException {
        URL url = new URL(src);
        FileOutputStream stream;

        try (ReadableByteChannel byteChannelForDownload = Channels.newChannel(url.openStream())) {
            stream = new FileOutputStream(PATH_MUSICS + name);
            stream.getChannel().transferFrom(byteChannelForDownload, 0, Long.MAX_VALUE);
        }
    }

}