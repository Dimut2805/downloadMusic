import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.HashMap;

/**
 * Author Utin D. S 17it18
 */
public class DownloadUrl implements Constains {

    /**
     * Ищет ссылку на скачивание в исходном коде страницы сайта
     *
     * @return список ссылок на скачивание
     */
    public static HashMap<String,String> findAttributeMusic(String url) {
        HashMap<String, String> hashMap = new HashMap<>();
        Document document = createDocumentHTML(url);
        Elements elementsCodeMusics = document.select("ul.unstyled.songs").select("li.item");
        String dataIdMusic = elementsCodeMusics.attr("data-id");
        hashMap.put("nameMusic", elementsCodeMusics.select("[data-id=" + dataIdMusic + "]").attr("data-title"));
        hashMap.put("author", elementsCodeMusics.select("[data-id=" + dataIdMusic + "]").attr("data-artist"));
        hashMap.put("downloadUrl", elementsCodeMusics.select("li.play[data-id=" + dataIdMusic + "]").attr("data-url"));
        return hashMap;
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