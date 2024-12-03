package org.example.oop;

import java.util.Date;

public class AvailableBooks {
    private String title;
    private String author;
    private String genre;
    private String image;
    private Date date;

    public AvailableBooks(String title, String author, String genre, String image, Date date) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.image = image;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }
}
