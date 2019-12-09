package ru.pochemuchki.musicproject.constains;

import java.io.File;

public interface ResourseObjectsConstains {
    String PATH_TO_RES = "/ru/pochemuchki/musicproject/res/";
    String SRC_CSS_BLACK_STYLE = PATH_TO_RES + "css/stylesheetDark.css";
    String SRC_JPG_DEFAULT_TRACK_ICON = new File("src/" + PATH_TO_RES + "drawable/baseIcons/DedaultTrackIcon.png").getPath();
    String SRC_JPG_BASE_ICON_FOUND_MUSIC = new File("src/" + PATH_TO_RES + "drawable/baseIcons/BaseIconNotFoundMusic.jpg").getPath();
    String SRC_FXML_MAIN_LAYOUT = PATH_TO_RES + "layout/MainLayout.fxml";
    String SRC_FXML_EXIT = PATH_TO_RES + "layout/Exit.fxml";
}