package ru.pochemuchki.musicproject.model;

import javafx.concurrent.Task;
import javafx.scene.control.Button;
import ru.pochemuchki.musicproject.DownloadsSource;

import java.io.IOException;

public class DownloadButton extends ButtonAbstract {
    private AttributesMusic attributes;
    private Button downloadButton;

    public DownloadButton(String nameButton, AttributesMusic attributes) {
        super(nameButton);
        this.attributes = attributes;
        downloadButton = new Button(nameButton) {{
            setOnAction(event -> click());
        }};
    }

    public Button getDownloadButton() {
        return downloadButton;
    }

    @Override
    public void click() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                downloadImage();
                downloadMusic();
                return null;
            }
        };
        new Thread(task).start();
    }

    private void downloadMusic() {
        DownloadsSource.downloadSong(attributes.getUrlMusic(), attributes.getAuthor() + " - " + attributes.getNameMusic());
    }

    private void downloadImage() {
        try {
            DownloadsSource.downloadImage(attributes.getUrlImage(), attributes.getAuthor() + " - " + attributes.getNameMusic());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}