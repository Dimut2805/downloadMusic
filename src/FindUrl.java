import java.io.*;
import java.net.URL;

public class FindUrl {
    public static void findUrl() {
        String string;
        String string2;
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\inputFile.txt"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\outputFile.txt"))) {
            while ((string = bufferedReader.readLine()) != null) {
                URL url = new URL(string);
                try (BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    while ((string2 = bufferedReader1.readLine()) != null) {
                        if ((string2.contains("musicset-track-link") || string2.contains("class=\"play\""))) {
                            bufferedWriter.write(as(string2) + System.lineSeparator());
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String as(String string) {
        StringBuilder s = new StringBuilder();
        String d = dima(string);
        for (int i = 0; i < d.length(); i++) {
            if ((d.charAt(i) + "").equals("\"")) {
                return s.toString();
            }
            s.append(d.charAt(i));
        }
        return String.valueOf(s);
    }

    private static String dima(String string) {
        return string.contains("data-url=\"") ? string.substring(string.indexOf("data-url=\"") + 10) : "";
    }
}