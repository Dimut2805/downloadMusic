import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DownloadUrl {
    public void writeDownloadUrl(String urlFile) {
        write(urlFile, Objects.requireNonNull(findDownloadUrl()));
    }

    private static List<String> findDownloadUrl() {
        List<String> downloadUrl = new ArrayList<>();
        List<String> urlSites = read("src\\inputUrl.txt");
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

    private static URL createUrl(String urlSite) {
        try {
            return new URL(urlSite);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void write(String urlFile, List<String> downloadUrl) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(urlFile))) {
            for (String stringDownloadUrl : downloadUrl) {
                bufferedWriter.write(stringDownloadUrl + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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