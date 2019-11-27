package ru.pochemuchki.musicproject;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Song implements Constains {

    public void downloadSong(String downloadUrl,String name) {
            try {
                    download(downloadUrl, name + ".mp3");
                } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public void playSong(String nameSong) {
        File soundFile = new File(PATH_TO_MUSIC + nameSong );
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
            clip.stop();
            clip.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void download(String strUrl, String nameSound) throws IOException {
        URL url = new URL(strUrl);
        ReadableByteChannel byteChannelForDownload = Channels.newChannel(url.openStream());
        FileOutputStream stream = new FileOutputStream(nameSound);
        stream.getChannel().transferFrom(byteChannelForDownload, 0, Long.MAX_VALUE);
        Path sound = Paths.get(nameSound);
        Files.copy(sound,Paths.get(PATH_TO_MUSIC + nameSound),REPLACE_EXISTING);
        stream.close();
        byteChannelForDownload.close();
        Files.delete(sound);
    }

}
