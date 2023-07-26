package com.example.grocery_new;

import android.content.Context;

public class MovieModel {
    private String title, genre, year;
    String img,description;
    Context context;
    public MovieModel() {
    }
    public MovieModel(String img, String title, String genre, String year,String description,Context context) {
        this.img=img;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.description=description;
        this.context=context;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.title = name;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
