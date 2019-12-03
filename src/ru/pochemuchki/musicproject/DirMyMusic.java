package ru.pochemuchki.musicproject;

import java.io.File;
import java.util.ArrayList;

public class DirMyMusic implements Constains {

    public static ArrayList<String> getMusics() {
        File directory = new File(PATH_MUSICS);
        ArrayList<String> musics = new ArrayList<>();
        for (String file : directory.list()) {
            if (file.contains(".mp3")) {
                musics.add(file);
            }
        }
        return musics;
    }
    public static boolean findPicture(String src, String namePicture) {
        File directory = new File(PATH_IMAGE+src);
        for (String file : directory.list()) {
            if(file.equals(namePicture)) {
                return true;
            }
        }
        return false;
    }

    public static void deleteMusic(String nameDelete) {
        File music = new File(PATH_MUSICS + "\\" + nameDelete);
        music.delete();
    }
}