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

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmArtUrl() {
        return mArtUrl;
    }
}

