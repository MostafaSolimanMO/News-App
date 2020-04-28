package com.example.daii.udacitynewsapp.Model.Repository;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.example.daii.udacitynewsapp.Model.Article;
import com.example.daii.udacitynewsapp.Model.News;
import com.example.daii.udacitynewsapp.Remote.NewsServiceApi;
import com.example.daii.udacitynewsapp.Remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

public class NewsRepository {
    public final static String BASE_URL = "https://newsapi.org/";
    MutableLiveData<List<Article>> mutableLiveData;
    Application application;

    public NewsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Article>> getMutableLiveData() {
        mutableLiveData = new MutableLiveData<>();
        SharedPreferences sharedPrefs = getDefaultSharedPreferences(application);
        String country = sharedPrefs.getString("country", "us");
        String category = sharedPrefs.getString("category", "general");
        NewsServiceApi serviceApi = RetrofitClient.getClient(BASE_URL).create(NewsServiceApi.class);
        serviceApi.getAllNews(country, category)
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if (response.isSuccessful()) {
                            News newsRes = response.body();
                            List<Article> articleList = newsRes.getArticles();
                            mutableLiveData.setValue(articleList);
                        }
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                    }
                });
        return mutableLiveData;
    }
}
