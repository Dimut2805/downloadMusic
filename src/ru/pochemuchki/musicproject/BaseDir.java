package ru.pochemuchki.musicproject;

import java.io.File;

public class BaseDir extends DirMyMusic implements Constains {
    public static boolean findDir(String src, String nameDir) {
        File file = new File(PATH_IMAGE + src);
        for (String string : file.list()) {
            if (string.equals(nameDir)) {
                return true;
            }
        }
        return false;
    }
}
