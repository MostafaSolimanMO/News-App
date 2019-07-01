package com.example.daii.udacitynewsapp;

public class News {
    private String mTitle;
    private String mDescription;
    private String mSource;
    private String mAuthor;

    public News(String title, String description, String source, String author) {
        mTitle = title;
        mDescription = description;
        mSource = source;
        mAuthor = author;

    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmSource() {
        return mSource;
    }

    public String getmAuthor() {
        return mAuthor;
    }

}

