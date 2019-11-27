package ru.pochemuchki.musicproject;

import javafx.application.Platform;
import javafx.stage.Stage;

public class UpdatePathMusicThread extends Thread {
    GUI gui;

    UpdatePathMusicThread(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        gui.updatePathMusic();
                    }
                });
                Thread.sleep(2200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}