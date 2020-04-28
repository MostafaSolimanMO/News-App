package com.example.daii.udacitynewsapp.Remote;

import com.example.daii.udacitynewsapp.Model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsServiceApi {

    @GET("v2/top-headlines?apiKey=a36119b99ae8479aaf2301b6a976a388")
    Call<News> getAllNews(@Query("country") String country, @Query("category") String category);
}
