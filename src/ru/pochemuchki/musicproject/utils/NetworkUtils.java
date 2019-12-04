package ru.pochemuchki.musicproject.utils;

import java.io.File;
import java.net.MalformedURLException;

public interface NetworkUtils {

    default String createFileUrlString(File file) {
        try {
            return file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
