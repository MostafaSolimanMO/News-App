package com.example.daii.udacitynewsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<News>>, SharedPreferences.OnSharedPreferenceChangeListener, NewsAdapter.NewsOnClick {

    private static final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines?";
    private static final String API_KEY = "a36119b99ae8479aaf2301b6a976a388";
    String restartLoader;
    String initLoader;
    private ProgressBar progressBar;
    private TextView mEmptyTextView;
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.loading_bar);

        mEmptyTextView = findViewById(R.id.text);
        mEmptyTextView.setText("No news available");

        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new NewsAdapter(new ArrayList<News>(), this);
        mRecyclerView.setAdapter(mAdapter);
        LoaderAndConnection(initLoader);
    }

    @Override
    public void onClick(String uriNews) {
        Uri newUri = Uri.parse(uriNews);
        Intent linkNew = new Intent(Intent.ACTION_VIEW, newUri);
        startActivity(linkNew);
    }

    public void LoaderAndConnection(String loader) {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            mEmptyTextView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            LoaderManager loaderManager = getSupportLoaderManager();
            if (loader == initLoader) {
                loaderManager.initLoader(1, null, MainActivity.this);
            }
            if (loader == restartLoader) {
                getSupportLoaderManager().restartLoader(1, null, this);
            }
        } else if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {
            if (loader == initLoader) {
                mEmptyTextView.setText("No Internet Connection");
            }
            if (loader == restartLoader) {
                mAdapter.clear();
                mEmptyTextView.setVisibility(View.VISIBLE);
                mEmptyTextView.setText("No Internet Connection");
            }
        }
    }

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String country = sharedPrefs.getString(
                "country",
                "us");
        String topic = sharedPrefs.getString(
                "category",
                "general");

        Uri baseUri = Uri.parse(NEWS_API_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("country", country);
        uriBuilder.appendQueryParameter("category", topic);
        uriBuilder.appendQueryParameter("apiKey", API_KEY);

        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> news) {
        progressBar.setVisibility(View.GONE);
        mAdapter.notifyDataSetChanged();
        if (news != null && !news.isEmpty()) {
            mAdapter.setNewsData(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals("country") || key.equals("category")) {
            mAdapter.notifyDataSetChanged();
            mEmptyTextView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            getSupportLoaderManager().restartLoader(1, null, this);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Settings) {
            Intent intent = new Intent(MainActivity.this, settings_activity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.About) {
            Intent intent = new Intent(MainActivity.this, about.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.Refresh) {
            LoaderAndConnection(restartLoader);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
