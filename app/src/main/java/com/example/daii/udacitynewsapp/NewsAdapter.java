package com.example.daii.udacitynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    private static final String SPLIT_MARK = " - ";

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }
        News items = getItem(position);

        String allTitle = items.getmTitle();
        String[] finalTitle = allTitle.split(SPLIT_MARK);
        String title = finalTitle[0];
        String source = finalTitle[1];

        TextView titleText = listItemView.findViewById(R.id.title);
        titleText.setText(title);

        TextView sourceText = listItemView.findViewById(R.id.source);
        sourceText.setText(source);

        TextView descriptionText = listItemView.findViewById(R.id.description);
        descriptionText.setVisibility(View.GONE);
        if (items.getmDescription() != null) {
            descriptionText.setVisibility(View.VISIBLE);
            descriptionText.setText(items.getmDescription());
        }


        TextView authorText = listItemView.findViewById(R.id.author);
        authorText.setVisibility(View.GONE);
        if (items.getmAuthor() != null) {
            authorText.setVisibility(View.VISIBLE);
            authorText.setText(items.getmAuthor());
        }
        return listItemView;
    }
}
