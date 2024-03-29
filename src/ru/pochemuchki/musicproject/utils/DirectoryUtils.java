package ru.pochemuchki.musicproject.utils;

import ru.pochemuchki.musicproject.constains.Constains;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * Работа с директорией
 */
public class DirectoryUtils implements Constains {
    /**
     * Создание директории
     *
     * @param srcFir путь
     */
    public static void createDirectory(String srcFir) {
        new File(srcFir).mkdir();
    }

    /**
     * Копирование файла в другой репозиторий
     *
     * @param inFile  откуда
     * @param outFile куда
     */
    public static void copyFile(String inFile, String outFile) {
        try {
            Files.copy(Paths.get(inFile),
                    Paths.get(outFile),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Поиск объекта в директории
     *
     * @param srcObject полный путь объекта
     * @return результат поиска
     */
    public static boolean findObjectInDir(String srcObject) {
        return new File(srcObject).exists();

    }

    /**
     * Список музыки
     *
     * @return список музыки
     */
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

    /**
     * Удаление музыки
     *
     * @param nameDelete путь к директории
     */
    public static void deleteMusic(String nameDelete) {
        File music = new File(PATH_MUSICS + "\\" + nameDelete);
        music.delete();
    }
}