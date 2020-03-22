package com.example.daii.udacitynewsapp;

public class News {
    private String mTitle;
    private String mDescription;
    private String mAuthor;
    private String mArtUrl;

    public News(String title, String description, String author, String url) {
        mTitle = title;
        mDescription = description;
        mAuthor = author;
        mArtUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getArtUrl() {
        return mArtUrl;
    }
}

