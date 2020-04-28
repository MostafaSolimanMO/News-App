package com.example.daii.udacitynewsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.daii.udacitynewsapp.Model.Article;
import com.example.daii.udacitynewsapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> articleList;
    private NewsOnClick onClickItem;

    public NewsAdapter(NewsOnClick listener) {
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
        Article article = articleList.get(position);
        holder.setDetails(article);
    }

    @Override
    public int getItemCount() {
        if (articleList != null)
            return articleList.size();
        else return 0;
    }

    public void setNewsData(List<Article> articlesData) {
        articleList = articlesData;
        notifyDataSetChanged();
    }

    public void clear() {
        int size = articleList.size();
        articleList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public interface NewsOnClick {
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
            Article articles = articleList.get(getAdapterPosition());
            String uriNews = articles.getUrl();
            onClickItem.onClick(uriNews);
        }

        public void setDetails(Article articles) {
            String allTitle = articles.getTitle();
            String[] splitTitle = allTitle.split(SPLIT_MARK);
            String title = splitTitle[0];
            String source = splitTitle[1];

            mTitleText.setText(title);
            mSourceText.setText(source);
            mDescriptionText.setVisibility(View.GONE);

            String description = articles.getDescription();
            if (description != null) {
                if (description.contains(SPLIT_MARK)) {
                    String[] finalDescription = description.split(SPLIT_MARK);
                    description = finalDescription[0];
                }
                mDescriptionText.setVisibility(View.VISIBLE);
                mDescriptionText.setText(description);
            }

            mAuthorText.setVisibility(View.GONE);
            if (articles.getAuthor() != null && articles.getAuthor().length() < 20) {
                mAuthorText.setVisibility(View.VISIBLE);
                mAuthorText.setText(articles.getAuthor());
            }
        }
    }
}

