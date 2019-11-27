package ru.pochemuchki.musicproject;

import javafx.application.Platform;

public class UpdatePathMusicThread extends Thread {
    GUI gui;

    UpdatePathMusicThread(GUI gui) {
        this.gui = gui;
        gui.createGUI();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        gui.fillPathMusic();
                    }
                });
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}