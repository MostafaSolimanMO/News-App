package com.example.daii.udacitynewsapp;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader {
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        ArrayList<News> news = (ArrayList<News>) QueryUtils.fetchNewsData(mUrl);
        return news;
    }
}
