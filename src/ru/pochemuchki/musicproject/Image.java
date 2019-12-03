package ru.pochemuchki.musicproject;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Класс для скачивания картинки
 */
public class Image implements Constains {

    /**
     * Методля для скачивания кртинки который принимает ссылку на картинкуи и имя файла
     *
     * @param src       - ссылка на картинку
     * @param nameImage - имя файлв
     */
    public void downloadImage(String src, String nameImage) throws IOException {
        String path = PATH_IMAGE + "\\" + nameImage;
        InputStream in;
        URL connection = new URL(src);
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

    public void removeImage(String nameImg){
        File imageFile = new File(PATH_IMAGE + "\\" + nameImg);
        imageFile.delete();
    }
}
