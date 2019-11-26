package ru.pochemuchki.musicproject;

import java.io.File;
import java.util.ArrayList;

public class Directory implements Constains {
    private String path;

    Directory(String path) {
        this.path = path;
    }

    public ArrayList<String> getMusics() {
        File directory = new File(path);
        ArrayList<String> musics = new ArrayList<>();
        for (String file : directory.list()) {
            if (file.contains(".mp3")) {
                musics.add(file);
            }
        }
        return musics;
    }

    public void deleteMusic(String nameDelete) {
        File music = new File(path + "\\" + nameDelete);
        music.delete();
    }
}