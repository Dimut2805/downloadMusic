package ru.pochemuchki.musicproject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;

public class FindAttributeMusic implements Constains {

    /**
     * Ищет атрибуты в исходном коде страницы сайта
     *
     * @return список ссылок на скачивание
     */
    public static ArrayList<AttributesMusic> findAttributesMusic(String url) {
        ArrayList<AttributesMusic> arrayListAttributesMusic = new ArrayList<>();
        if (url != null) {
            Document document = createDocumentHTML(url);
            Elements elementsCodeMusics = document.select("ul.unstyled.songs").select("li.item");
            for (Element element : elementsCodeMusics) {
                arrayListAttributesMusic.add(new AttributesMusic(
                        element.select("li.item").attr("data-artist"),
                        element.select("li.item").attr("data-title"),
                        element.select("li.play").attr("data-url"),
                        element.select("img").attr("data-src")));
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