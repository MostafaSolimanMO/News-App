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

        TextView authorText = listItemView.findViewById(R.id.author);
        View linear = listItemView.findViewById(R.id.author_linear);
        linear.setVisibility(View.GONE);
        if (items.getmAuthor() != null) {
            authorText.setText(items.getmAuthor());
            linear.setVisibility(View.VISIBLE);
        }
        return listItemView;
    }
}
