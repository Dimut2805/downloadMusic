import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

public interface Constains {
    String DOWNLOAD_URL = "src\\downloadUrl.txt";
    HashMap<String, String> HASH_MAP_SITE_TABS = new HashMap<>() {
        {
            put("Не выбрано", null);
            put("Топ-100", "https://muzika.vip");
            put("Русский поп", "https://muzika.vip/m-genres/русский-поп-7071");
            put("Русский рок", "https://muzika.vip/m-genres/русский-рок-7070");
            put("Дабстэп", "https://muzika.vip/m-genres/dubstep-7043");
        }
    };
    ObservableList<String> OBSERVABLE_LIST_SITE_TABS = FXCollections.observableArrayList(
            "Не выбрано",
            "Топ-100",
            "Русский поп",
            "Русский рок",
            "Дабстэп");
}
