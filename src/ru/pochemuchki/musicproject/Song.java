package ru.pochemuchki.musicproject;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Класс для работы с музыкой.
 * Класс скачивает музыку, воспроизводит музыку
 */
public class Song implements Constains {


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
     * Воспроизводит музыку, на вход принимает имя файла
     *
     * @param nameSong - имя файла
     */
    public void playSong(String nameSong) throws FileNotFoundException {
        AdvancedPlayer player;

        try {
            InputStream threath = new FileInputStream(PATH_MUSICS + "\\" + nameSong);
            AudioDevice auDev = new JavaSoundAudioDevice();

            player = new AdvancedPlayer(threath, auDev);
            player.play();

        } catch (JavaLayerException e) {
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
    private static void download(String src, String name) throws IOException {
        URL url = new URL(src);
        FileOutputStream stream;

        try (ReadableByteChannel byteChannelForDownload = Channels.newChannel(url.openStream())) {
            stream = new FileOutputStream(PATH_MUSICS + "\\" + name);
            stream.getChannel().transferFrom(byteChannelForDownload, 0, Long.MAX_VALUE);
        }
    }
}
