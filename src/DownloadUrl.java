import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Author Utin D. S 17it18
 */
public class DownloadUrl implements Constains{
    /**
     * Записывает ссылку на скачивание в текстовый файл
     *
     * @param urlFile
     */
    public void writeDownloadUrl(String urlFile) {
        write(urlFile, Objects.requireNonNull(findDownloadUrl()));
    }

    /**
     * Ищет ссылку на скачивание в исходном коде страницы сайта
     *
     * @return список ссылок на скачивание
     */
    private static List<String> findDownloadUrl() {
        List<String> downloadUrl = new ArrayList<>();
        List<String> urlSites = read(INPUT_URL);
        assert urlSites != null;
        for (String stringUrlSite : urlSites) {
            URL url = createUrl(stringUrlSite);
            assert url != null;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")");
                Matcher matcher = email_pattern.matcher(bufferedReader.lines().collect(Collectors.joining(System.lineSeparator())));
                if (matcher.find()) {
                    downloadUrl.add(matcher.group());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return downloadUrl.size() != 0 ? downloadUrl : null;
    }

    /**
     * Создает URL сайта
     *
     * @param urlSite url сайта
     * @return url сайта
     */
    private static URL createUrl(String urlSite) {
        try {
            return new URL(urlSite);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Записывает ссылки на скачивание в текстовый файл
     *
     * @param urlFile     путь к текстовому файлу для записи
     * @param downloadUrl список ссылок на скачивание
     */
    private static void write(String urlFile, List<String> downloadUrl) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(urlFile))) {
            for (String stringDownloadUrl : downloadUrl) {
                bufferedWriter.write(stringDownloadUrl + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Читает из текстового файла url сайтов
     *
     * @param urlFile путь к текстовому файлу
     * @return список ссылок сайтов
     */
    private static List<String> read(String urlFile) {
        String urlSite;
        List<String> listUrl = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(urlFile))) {
            while ((urlSite = bufferedReader.readLine()) != null) {
                listUrl.add(urlSite);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listUrl.size() != 0 ? listUrl : null;
    }
}