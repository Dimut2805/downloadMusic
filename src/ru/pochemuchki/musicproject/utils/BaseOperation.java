package ru.pochemuchki.musicproject.utils;

import ru.pochemuchki.musicproject.constains.ResourseObjectsConstains;

public class BaseOperation extends DirectoryUtils implements ResourseObjectsConstains {

    public void installationBaseObjects() {
        if (!findObjectInDir(BASE_DIR_DOWNLOADER_MUSIC_PICTURE)) {
            createDirectory(BASE_DIR_DOWNLOADER_MUSIC_PICTURE);
        }
        if (!findObjectInDir(BASE_DIR_BASE_PICTURE)) {
            createDirectory(BASE_DIR_BASE_PICTURE);
        }
        if (!findObjectInDir(BASE_IMAGE_JPG_BASE_ICON)) {
            copyFile(getClass().getResource(SRC_JPG_DEFAULT_TRACK_ICON).getFile(), BASE_IMAGE_JPG_BASE_ICON);
        }
        if (!findObjectInDir(BASE_IMAGE_JPG_NOT_FOUND_MUSIC)) {
            copyFile(getClass().getResource(SRC_JPG_BASE_ICON_FOUND_MUSIC).getFile(), BASE_IMAGE_JPG_NOT_FOUND_MUSIC);
        }
    }
}