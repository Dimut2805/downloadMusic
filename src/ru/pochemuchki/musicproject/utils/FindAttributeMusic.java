package ru.pochemuchki.musicproject.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.pochemuchki.musicproject.objects.AttributesMusic;
import ru.pochemuchki.musicproject.constains.Constains;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FindAttributeMusic implements Constains {

    /**
     * Ищет атрибуты в исходном коде страницы сайта
     *
     * @return список ссылок на скачивание
     */
    public static List<AttributesMusic> findAttributesMusic(String url) {
        List<AttributesMusic> listAttributesMusic = new ArrayList<>();
        if (url != null) {
            Document document = createDocumentHTML(url);
            Elements elementsCodeMusics = document.select("ul.unstyled.songs").select("li.item");
            for (Element element : elementsCodeMusics) {
                listAttributesMusic.add(new AttributesMusic(
                        element.select("li.item").attr("data-artist"),
                        element.select("li.item").attr("data-title"),
                        element.select("li.play").attr("data-url"),
                        element.select("img").attr("data-src")));
            }
        }
        return listAttributesMusic;
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