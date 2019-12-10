package ru.pochemuchki.musicproject;

import ru.pochemuchki.musicproject.constains.Constains;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


/**
 * Класс для скачивания ресурсов
 *
 * Методы :
 *         downloadSong - предназначен для скачивания музыки
 *         download - вспомогательный метод для скачиваниея музыки
 *         downloadImage - предназначен для скачивания картинок
 *
 */
public class DownloadsSource implements Constains {
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
    private static void download(String src, String name) throws IOException {
        URL url = new URL(src);
        FileOutputStream stream;

        try (ReadableByteChannel byteChannelForDownload = Channels.newChannel(url.openStream())) {
            stream = new FileOutputStream(PATH_MUSICS + name);
            stream.getChannel().transferFrom(byteChannelForDownload, 0, Long.MAX_VALUE);
        }
    }

    /**
     * Методля для скачивания кртинки который принимает ссылку на картинкуи и имя файла
     *
     * @param src       - ссылка на картинку
     * @param nameImage - имя файлв
     */
    public void downloadImage(String src, String nameImage) throws IOException {
        String path = PATH_IMAGE + "\\DownloaderMusicPicture\\" + nameImage;
        InputStream in;
        URL connection = new URL("https://muzika.vip/"+src);
        HttpURLConnection urlConn;
        try {
            urlConn = (HttpURLConnection) connection.openConnection();
            urlConn.setRequestMethod("GET");
            urlConn.connect();
            in = urlConn.getInputStream();
            OutputStream writer = new FileOutputStream(path);
            byte buffer[] = new byte[1];
            int c = in.read(buffer);
            while (c > 0) {
                writer.write(buffer, 0, c);
                c = in.read(buffer);
            }
            writer.flush();
            writer.close();
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
