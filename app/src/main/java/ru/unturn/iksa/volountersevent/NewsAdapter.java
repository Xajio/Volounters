package ru.unturn.iksa.volountersevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Iksa on 22.05.2018.
 */

public class NewsAdapter extends ArrayAdapter<News>{
    public NewsAdapter(Context context, News[] arr) {
        super(context, R.layout.adapters_news_list, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final News news = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapters_news_list, null);
        }

// Заполняем адаптер
        ImageByUrl url = new ImageByUrl((ImageView) convertView.findViewById(R.id.newsImage));
        url.doInBackground(news.linkImage);
        ((TextView) convertView.findViewById(R.id.newsShortDescription)).setText(news.shortNews);
        return convertView;
    }
}
