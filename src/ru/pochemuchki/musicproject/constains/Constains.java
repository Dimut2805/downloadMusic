package ru.pochemuchki.musicproject.constains;

import java.util.HashMap;

public interface Constains {
    String NAME_USER = System.getProperty("user.name");
    String PATH_MUSICS = "C:\\Users\\" + NAME_USER + "\\Music\\";
    String PATH_IMAGE = "C:\\Users\\" + NAME_USER + "\\Pictures\\";
    String BASE_DIR_BASE_PICTURE = PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\";
    String BASE_IMAGE_JPG_NOT_FOUND_MUSIC = PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\BaseIconNotFoundMusic.jpg";
    String BASE_IMAGE_JPG_BASE_ICON = PATH_IMAGE + "\\DownloaderMusicPicture\\basePicture\\DedaultTrackIcon.jpg";
    String BASE_DIR_DOWNLOADER_MUSIC_PICTURE = PATH_IMAGE + "\\DownloaderMusicPicture\\";
    HashMap<String, String> HASH_MAP_SECTIONS = new HashMap<String, String>() {
        {
            put("Не выбрано", null);
            put("Топ-100", "https://muzika.vip");
            put("Billie Eilish", "https://muzika.vip/m-artists/billie-eilish-121989");
            put("Григорий Лепс", "https://muzika.vip/m-artists/григорий-лепс-6298");
            put("Группа Кино", "https://muzika.vip/m-artists/виктор-цой-6257");
            put("Макс Корж", "https://muzika.vip/m-artists/макс-корж-9229");
            put("Lindsey Stirling", "https://muzika.vip/m-artists/lindsey-stirling-10942");
        }
    };
}