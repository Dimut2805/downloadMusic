package ru.pochemuchki.musicproject;

public class AttributesMusic {
    private String author, nameMusic, urlMusic, urlImage;

    AttributesMusic(String author, String nameMusic, String urlMusic, String urlImage) {
        this.author = author;
        this.nameMusic = nameMusic;
        this.urlMusic = urlMusic;
        this.urlImage = urlImage;
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
