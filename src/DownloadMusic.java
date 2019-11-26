import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadMusic {
    private static final String PATH_TO_MUSIC = "src\\music";
    private static final String URL_FOR_DOWNLOAD_MUSIC = "src\\outputFile.txt";


    private static void downloadMusic(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
        FileOutputStream stream = new FileOutputStream(PATH_TO_MUSIC);
        stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        stream.close();
        byteChannel.close();
    }
}
