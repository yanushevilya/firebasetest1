package com.example.firebasetest1;

public class ArtItem {
    private String author;
    private String img;

    public ArtItem() {
    }

    public ArtItem(String author, String img) {
        this.author = author;
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public String getImg() {
        return img;
    }
}
