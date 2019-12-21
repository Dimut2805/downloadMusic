package ru.pochemuchki.musicproject.model;

public class AttributesMusic {
    private String author, nameMusic, urlMusic, urlImage;
    private int numberMusic;

    public AttributesMusic(int numberMusic, String author, String nameMusic, String urlMusic, String urlImage) {
        this.author = author;
        this.nameMusic = nameMusic;
        this.urlMusic = urlMusic;
        this.urlImage = urlImage;
        this.numberMusic = numberMusic;
    }

    public int getNumberMusic() {
        return numberMusic;
    }

    public String getNameMusic() {
        return nameMusic;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getUrlMusic() {
        return urlMusic;
    }
}