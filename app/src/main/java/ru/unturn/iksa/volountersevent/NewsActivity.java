package ru.unturn.iksa.volountersevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        News[] events1 = RequestsForServer.getNews();
        final NewsAdapter adapter = new NewsAdapter(this, events1);
        ListView lv = (ListView) findViewById(R.id.listViewNews);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                News news = adapter.getItem(pos);
                Intent intent = new Intent(NewsActivity.this, NewsOneActivity.class);
                intent.putExtra(News.class.getCanonicalName(), news);
                startActivity(intent);
            }
        });
    }
}
