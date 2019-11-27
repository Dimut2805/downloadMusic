package ru.pochemuchki.musicproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.HashMap;

public interface Constains {
    String DOWNLOAD_URL = "src\\downloadUrl.txt";
    String PATH_TO_MUSIC = "C:\\Users\\" + System.getProperty("user.name") + "\\Music\\"; // for use file in this directory
    String PATH_TO_IMAGE = "srs\\image";
    String PATH_MUSICS = "C:\\Users\\" + System.getProperty("user.name") + "\\Music";


    HashMap<String, String> HASH_MAP_SITE_TABS = new HashMap<String, String>() {
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
    ObservableList<String> OBSERVABLE_LIST_SITE_TABS = FXCollections.observableArrayList(
            "Не выбрано",
            "Топ-100",
            "Billie Eilish",
            "Григорий Лепс",
            "Группа Кино",
            "Макс Корж",
            "Lindsey Stirling");
}
