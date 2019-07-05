package com.example.daii.udacitynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsAdapter extends ArrayAdapter<News> {

    private static final String SPLIT_MARK = " - ";

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }
        News items = getItem(position);

        String allTitle = items.getmTitle();
        String[] splitTitle = allTitle.split(SPLIT_MARK);
        String title = splitTitle[0];
        String source = splitTitle[1];

        TextView titleText = listItemView.findViewById(R.id.title);
        titleText.setText(title);

        TextView sourceText = listItemView.findViewById(R.id.source);
        sourceText.setText(source);


        TextView descriptionText = listItemView.findViewById(R.id.description);
        descriptionText.setVisibility(View.GONE);

        String description = items.getmDescription();
        if (description != null) {
            if (description.contains(SPLIT_MARK)) {
                String[] finalDescription = description.split(SPLIT_MARK);
                description = finalDescription[0];
            }
            descriptionText.setVisibility(View.VISIBLE);
            descriptionText.setText(description);
        }

        TextView authorText = listItemView.findViewById(R.id.author);
        authorText.setVisibility(View.GONE);
        if (items.getmAuthor() != null && items.getmAuthor().length() < 20) {
            authorText.setVisibility(View.VISIBLE);
            authorText.setText(items.getmAuthor());
        }
        return listItemView;
    }
}
