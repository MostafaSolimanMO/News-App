package com.example.daii.udacitynewsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private ArrayList<News> mNews;

    public NewsAdapter(ArrayList<News> news) {
        mNews = news;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News news = mNews.get(position);
        holder.setDetails(news);
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public void setNewsData(ArrayList<News> newsData) {
        mNews = newsData;
        notifyDataSetChanged();
    }

    public void clear() {
        int size = mNews.size();
        mNews.clear();
        notifyItemRangeRemoved(0, size);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private static final String SPLIT_MARK = " - ";
        public final TextView mTitleText;
        public final TextView mSourceText;
        public final TextView mDescriptionText;
        public final TextView mAuthorText;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.title);
            mSourceText = itemView.findViewById(R.id.source);
            mDescriptionText = itemView.findViewById(R.id.description);
            mAuthorText = itemView.findViewById(R.id.author);

        }

        public void setDetails(News news) {
            String allTitle = news.getmTitle();
            String[] splitTitle = allTitle.split(SPLIT_MARK);
            String title = splitTitle[0];
            String source = splitTitle[1];

            mTitleText.setText(title);
            mSourceText.setText(source);
            mDescriptionText.setVisibility(View.GONE);

            String description = news.getmDescription();
            if (description != null) {
                if (description.contains(SPLIT_MARK)) {
                    String[] finalDescription = description.split(SPLIT_MARK);
                    description = finalDescription[0];
                }
                mDescriptionText.setVisibility(View.VISIBLE);
                mDescriptionText.setText(description);
            }

            mAuthorText.setVisibility(View.GONE);
            if (news.getmAuthor() != null && news.getmAuthor().length() < 20) {
                mAuthorText.setVisibility(View.VISIBLE);
                mAuthorText.setText(news.getmAuthor());
            }
        }
    }
}

