package ru.pochemuchki.musicproject;

public class BaseOperation extends DirectoryUtils {

    public void installationBaseObjects() {
        if (!findObjectInDir(BASE_DIR_DOWNLOADER_MUSIC_PICTURE)) {
            createDirectory(BASE_DIR_DOWNLOADER_MUSIC_PICTURE);
        }
        if (!findObjectInDir(BASE_DIR_BASE_PICTURE)) {
            createDirectory(BASE_DIR_BASE_PICTURE);
        }
        if (!findObjectInDir(BASE_IMAGE_JPG_BASE_ICON)) {
            copyFile(SRC_IMAGE_BASE_ICON, BASE_IMAGE_JPG_BASE_ICON);
        }
        if (!findObjectInDir(BASE_IMAGE_JPG_NOT_FOUND_MUSIC)) {
            copyFile(SRC_IMAGE_NOT_FOUND, BASE_IMAGE_JPG_NOT_FOUND_MUSIC);
        }
    }
}