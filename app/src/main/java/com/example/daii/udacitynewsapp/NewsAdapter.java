package com.example.daii.udacitynewsapp;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private ArrayList<News> mNews;
    private NewsOnClick onClickItem;

    public NewsAdapter(ArrayList<News> news, NewsOnClick listener) {
        mNews = news;
        onClickItem = listener;
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

    interface NewsOnClick {
        void onClick(String uriNews);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String SPLIT_MARK = " - ";
        public final TextView mTitleText, mSourceText, mDescriptionText, mAuthorText;

        public NewsViewHolder(View view) {
            super(view);
            mTitleText = itemView.findViewById(R.id.title);
            mSourceText = itemView.findViewById(R.id.source);
            mDescriptionText = itemView.findViewById(R.id.description);
            mAuthorText = itemView.findViewById(R.id.author);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            News news = mNews.get(getAdapterPosition());
            String uriNews = news.getArtUrl();
            onClickItem.onClick(uriNews);
        }

        public void setDetails(News news) {
            String allTitle = news.getTitle();
            String[] splitTitle = allTitle.split(SPLIT_MARK);
            String title = splitTitle[0];
            String source = splitTitle[1];

            mTitleText.setText(title);
            mSourceText.setText(source);
            mDescriptionText.setVisibility(View.GONE);

            String description = news.getDescription();
            if (description != null) {
                if (description.contains(SPLIT_MARK)) {
                    String[] finalDescription = description.split(SPLIT_MARK);
                    description = finalDescription[0];
                }
                mDescriptionText.setVisibility(View.VISIBLE);
                mDescriptionText.setText(description);
            }

            mAuthorText.setVisibility(View.GONE);
            if (news.getAuthor() != null && news.getAuthor().length() < 20) {
                mAuthorText.setVisibility(View.VISIBLE);
                mAuthorText.setText(news.getAuthor());
            }
        }
    }
}

