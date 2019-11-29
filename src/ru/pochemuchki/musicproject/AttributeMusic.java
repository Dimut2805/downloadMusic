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
public class AttributeMusic implements Constains {

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
            for (Element element : elementsCodeMusics) {
                String[] array = new String[4];
                array[0] = element.select("li.item").attr("data-artist");
                array[1] = element.select("li.item").attr("data-title");
                array[2] = element.select("li.play").attr("data-url");
                array[3] = element.select("img").attr("data-src");
                arrayListAttributesMusic.add(array);
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