package ru.pochemuchki.musicproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;


/**
 * Author Utin D. S 17it18
 */
public class DownloadUrl implements Constains {

    /**
     * Ищет атрибуты в исходном коде страницы сайта
     *
     * @return список ссылок на скачивание
     */
    public static ArrayList<String[]> findAttributeMusic(String url) {
        ArrayList<String[]> arrayListAttributesMusic = new ArrayList<>();
        if (url != null) {
            Document document = createDocumentHTML(url);
            Elements elementsCodeMusics = document.select("ul.unstyled.songs").select("li.item");
            Elements elements = elementsCodeMusics.select("[data-id][data-artist]");
            StringBuilder prov = new StringBuilder();
            for (Element element : elements.select("li")) {
                if (element.hasAttr("data-id") && !prov.toString().contains(element.attr("data-id"))) {
                    String[] array = new String[3];
                    prov.append(element.attr("data-id")).append(" ");
                    String artist = elementsCodeMusics.select("[data-id=" + element.attr("data-id") + "]").attr("data-artist");
                    String nameMusic = elementsCodeMusics.select("[data-id=" + element.attr("data-id") + "]").attr("data-title");
                    String downloadUrl = elementsCodeMusics.select("li.play[data-id=" + element.attr("data-id") + "]").attr("data-url");
                    array[0] = artist;
                    array[1] = nameMusic;
                    array[2] = downloadUrl;
                    arrayListAttributesMusic.add(array);
                }
            }
        }
        return arrayListAttributesMusic;
    }

    private static Document createDocumentHTML(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}