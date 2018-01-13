package com.volkangurbuz.feedmewith;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VolkanGurbuz on 1/12/2018.
 */

public class NewAdapter extends BaseAdapter {

    ArrayList<NewsModel> newsModels;
    LayoutInflater layoutInflater;

    public NewAdapter(Activity activity, ArrayList<NewsModel> newsModels) {
        this.newsModels = newsModels;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return newsModels.size();
    }

    @Override
    public Object getItem(int position) {
        return newsModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = layoutInflater.inflate(R.layout.activity_listview, null);
        NewsModel newsModel = newsModels.get(position);

        TextView topic = row.findViewById(R.id.textViewTopic);
        TextView detail = row.findViewById(R.id.textViewDetail);

        topic.setText(newsModel.getTitle());
        detail.setText(newsModel.getDescription());

        return row;
    }
}
