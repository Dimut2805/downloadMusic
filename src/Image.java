import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Image implements Constains {

// ТЕСТОВЫЙ ВАРИАНТ
// НЕРАБОТАЕТ
    public void downloadImage() {
        try (BufferedReader imageFile = new BufferedReader(new FileReader(DOWNLOAD_URL))) {
            String image;
            int count = 0;
            try {
                while ((image = imageFile.readLine()) != null) {
                    download(image, PATH_TO_IMAGE + String.valueOf(count) + ".jpg");
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void download(String strUrl, String file) throws IOException {
        URL url = new URL(strUrl);
        ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
        FileOutputStream stream = new FileOutputStream(file);
        stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        stream.close();
        byteChannel.close();
    }

}
