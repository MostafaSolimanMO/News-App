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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }
        News items = getItem(position);

        TextView titleText = listItemView.findViewById(R.id.title);
        titleText.setText(items.getmTitle());

        TextView descriptionText = listItemView.findViewById(R.id.description);
        descriptionText.setText(items.getmDescription());

        TextView sourceText = listItemView.findViewById(R.id.source);
        sourceText.setText(items.getmSource());

        TextView authorText = listItemView.findViewById(R.id.author);
        authorText.setText(items.getmAuthor());

        return listItemView;
    }
}
