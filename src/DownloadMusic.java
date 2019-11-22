import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadMusic {
    private static final String PATH_TO_MUSIC = "src\\music";
    private static final String URL_FOR_DOWNLOAD_MUSIC = "src\\outputFile.txt";

    DownloadMusic() {
        try (BufferedReader musicFile = new BufferedReader(new FileReader(URL_FOR_DOWNLOAD_MUSIC))) {
            String music;
            int count = 0;
            try {
                while ((music = musicFile.readLine()) != null) {
                    downloadMusic(music, PATH_TO_MUSIC + String.valueOf(count) + ".mp3");
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void downloadMusic(String strUrl, String file) throws IOException {
        URL url = new URL(strUrl);
        ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
        FileOutputStream stream = new FileOutputStream(file);
        stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        stream.close();
        byteChannel.close();
    }
}
